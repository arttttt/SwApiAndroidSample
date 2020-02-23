package com.arttttt.swapisamplemvi.feature.heroesfeature

import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature.*
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature.Action.Execute
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature.Action.LoadHeroes
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature.Effect.*
import com.arttttt.swapisamplemvi.utils.extensions.toObservable
import com.badoo.mvicore.element.*
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
import javax.inject.Inject

class HeroesFeature @Inject constructor(
    swRepository: SwRepository
) : BaseFeature<Wish, Action, Effect, State, News>(
    initialState = State(
        1,
        false,
        emptyList()
    ),
    wishToAction = { Execute(it) },
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(swRepository),
    reducer = ReducerImpl(),
    postProcessor = PostProcessorImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val currentPage: Int,
        val isLoading: Boolean,
        val heroes: List<Hero>
    )

    sealed class Wish {
        object RefreshHeroes: Wish()
        object LoadMoreHeroes: Wish()
        class OpenHeroDetails(val index: Int): Wish()
    }

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
        object LoadHeroes: Action()
        object DropSavedHeroes: Action()
    }

    sealed class Effect {
        object NoEffect: Effect()
        object Loading: Effect()
        object CurrentPageIncreased: Effect()
        object SavedHeroesDropped: Effect()
        data class HeroesIsLoaded(val heroes: List<Hero>): Effect()
    }

    sealed class News {
        class HeroSelected(val name: String): News()
    }

    class BootStrapperImpl : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> {
            return LoadHeroes.toObservable()
        }
    }

    class ActorImpl(
        private val swRepository: SwRepository
    ) : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<out Effect> {
            return when (action) {
                is Execute -> dispatchWish(action.wish)
                is Action.DropSavedHeroes,
                is LoadHeroes -> swRepository
                    .getHeroesPage(state.currentPage)
                    .map<Effect>(Effect::HeroesIsLoaded)
                    .onErrorReturn { throwable ->
                        if (throwable is HttpException && throwable.code() == 404) {
                            return@onErrorReturn HeroesIsLoaded(emptyList())
                        }

                        throw throwable
                    }
                    .startWith(Loading)
            }.observeOn(AndroidSchedulers.mainThread())
        }

        private fun dispatchWish(wish: Wish): Observable<out Effect> {
            return when (wish) {
                is Wish.RefreshHeroes -> SavedHeroesDropped.toObservable()
                is Wish.OpenHeroDetails -> NoEffect.toObservable()
                is Wish.LoadMoreHeroes -> CurrentPageIncreased.toObservable()
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is NoEffect -> state
                is Loading -> state.copy(isLoading = true)
                is HeroesIsLoaded -> state.copy(heroes = state.heroes + effect.heroes, isLoading = false)
                is CurrentPageIncreased -> state.copy(currentPage = state.currentPage + 1)
                is SavedHeroesDropped -> state.copy(currentPage = 1, heroes = emptyList())
            }
        }
    }

    class PostProcessorImpl : PostProcessor<Action, Effect, State> {
        override fun invoke(action: Action, effect: Effect, state: State): Action? {
            return when (effect) {
                is CurrentPageIncreased -> LoadHeroes
                is SavedHeroesDropped -> Action.DropSavedHeroes
                else -> null
            }
        }
    }

    class NewsPublisherImpl : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? {
            return when (action) {
                is Execute -> when (action.wish) {
                    is Wish.OpenHeroDetails -> News.HeroSelected(state.heroes.elementAt(action.wish.index).name)
                    else -> null
                }
                else -> null
            }
        }
    }
}
