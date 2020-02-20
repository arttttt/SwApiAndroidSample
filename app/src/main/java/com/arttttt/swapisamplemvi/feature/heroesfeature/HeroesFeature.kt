package com.arttttt.swapisamplemvi.feature.heroesfeature

import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.Serializable
import javax.inject.Inject

class HeroesFeature @Inject constructor(
    swRepository: SwRepository
): ActorReducerFeature<HeroesFeature.Wish, HeroesFeature.Effect, HeroesFeature.State, HeroesFeature.News>(
    initialState = State(
        isLoading = false,
        heroes = emptyList()
    ),
    reducer = ReducerImpl(),
    actor = ActorImpl(swRepository),
    bootstrapper = BootstrapperImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val isLoading: Boolean,
        val heroes: List<Hero>
    ): Serializable

    sealed class Wish {
        object LoadHeroes: Wish()
        object RefreshHeroes: Wish()
        class OpenHeroDetails(val index: Int): Wish()
    }

    sealed class Effect {
        object NoEffect: Effect()
        object Loading: Effect()
        class HeroesLoaded(val heroes: List<Hero>): Effect()
    }

    sealed class News {
        class HeroSelected(val name: String): News()
    }

    class BootstrapperImpl: Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> {
            return Observable.just(Wish.LoadHeroes)
        }
    }

    class ReducerImpl: Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.Loading -> {
                    state.copy(
                        isLoading = true,
                        heroes = emptyList()
                    )
                }
                is Effect.HeroesLoaded -> {
                    state.copy(
                        isLoading = false,
                        heroes = effect.heroes
                    )
                }
                is Effect.NoEffect -> state
            }
        }
    }

    class ActorImpl(
        private val swRepository: SwRepository
    ): Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<out Effect> {
            return when (wish) {
                is Wish.LoadHeroes -> {
                    swRepository
                        .getHeroes()
                        .map<Effect>(Effect::HeroesLoaded)
                        .startWith(Observable.just(Effect.Loading))
                }
                is Wish.RefreshHeroes -> {
                    swRepository
                        .deleteAllHeroes()
                        .andThen(swRepository.getHeroes())
                        .map<Effect>(Effect::HeroesLoaded)
                        .startWith(Observable.just(Effect.Loading))
                }
                is Wish.OpenHeroDetails -> Observable.just(Effect.NoEffect)
            }.observeOn(AndroidSchedulers.mainThread())
        }
    }


    class NewsPublisherImpl: NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(action: Wish, effect: Effect, state: State): News? {
            return when (action) {
                is Wish.OpenHeroDetails -> News.HeroSelected(state.heroes.elementAt(action.index).name)
                else -> null
            }
        }
    }
}