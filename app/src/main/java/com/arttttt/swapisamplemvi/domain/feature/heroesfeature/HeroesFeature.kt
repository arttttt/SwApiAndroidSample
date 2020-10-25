package com.arttttt.swapisamplemvi.domain.feature.heroesfeature

import android.os.Bundle
import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.entity.Heroes
import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature.*
import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature.Action.Execute
import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature.Action.LoadHeroesPage
import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature.Effect.*
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.arttttt.swapisamplemvi.utils.extensions.toObservable
import com.badoo.mvicore.android.AndroidTimeCapsule
import com.badoo.mvicore.element.*
import com.badoo.mvicore.feature.BaseFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.Serializable

class HeroesFeature(
    timeCapsule: AndroidTimeCapsule,
    swRepository: SwRepository
) : BaseFeature<Wish, Action, Effect, State, News>(
    initialState = timeCapsule.state() ?: State(
        currentPage = 1,
        isLoading = false,
        isLoadingMore = false,
        fullData = false,
        heroes = emptyList()
    ),
    wishToAction = { Execute(it) },
    bootstrapper = BootStrapperImpl(
        initialState = timeCapsule.state() ?: State(
            currentPage = 1,
            isLoading = false,
            isLoadingMore = false,
            fullData = false,
            heroes = emptyList()
        )
    ),
    actor = ActorImpl(swRepository),
    reducer = ReducerImpl(),
    postProcessor = PostProcessorImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    companion object {
        private const val CAPSULE_KEY = "CAPSULE_KEY"
        private const val STATE_KEY = "STATE_KEY"

        private fun AndroidTimeCapsule.state() =
            get<Bundle>(CAPSULE_KEY)?.getSerializable(STATE_KEY) as? State

        private fun State.toParcelable() = Bundle().apply { putSerializable(STATE_KEY, this@toParcelable) }
    }

    init {
        timeCapsule.register(CAPSULE_KEY) {
            state
                .copy(
                    isLoading = false,
                    isLoadingMore = false
                )
                .toParcelable()
        }
    }

    data class State(
        val currentPage: Int,
        val fullData: Boolean,
        val isLoading: Boolean,
        val isLoadingMore: Boolean,
        val heroes: List<Hero>
    ) : Serializable

    sealed class Wish {
        object RefreshHeroes: Wish()
        object LoadMoreHeroes: Wish()
        class OpenHeroDetails(val index: Int): Wish()
    }

    sealed class Action {
        data class Execute(val wish: Wish) : Action()
        object LoadInitialData: Action()
        object LoadHeroesPage: Action()
    }

    sealed class Effect {
        object Loading: Effect()
        object InitialLoading: Effect()
        object CurrentPageIncreased: Effect()
        object SavedHeroesDropped: Effect()
        object HeroSelected: Effect()
        data class HeroesIsLoaded(val heroes: Heroes): Effect()
    }

    sealed class News {
        class HeroSelected(val hero: Hero): News()
    }

    class BootStrapperImpl(
        private val initialState: State
    ) : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> {
            return if (initialState.currentPage > 1) {
                Observable.empty()
            } else {
                Action.LoadInitialData.toObservable()
            }
        }
    }

    class ActorImpl(
        private val swRepository: SwRepository
    ) : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<out Effect> {
            return when (action) {
                is Execute -> dispatchWish(state, action.wish)
                is Action.LoadInitialData -> getHeroesPage(state.currentPage, InitialLoading)
                is LoadHeroesPage -> getHeroesPage(state.currentPage, Loading)
            }
        }

        private fun dispatchWish(state: State, wish: Wish): Observable<out Effect> {
            return when (wish) {
                is Wish.RefreshHeroes -> SavedHeroesDropped.toObservable()
                is Wish.OpenHeroDetails -> HeroSelected.toObservable()
                is Wish.LoadMoreHeroes -> {
                    if (state.isLoading || state.isLoadingMore || state.fullData) {
                        Observable.empty()
                    } else {
                        CurrentPageIncreased.toObservable()
                    }
                }
            }
        }

        private fun getHeroesPage(page: Int, initialEffect: Effect): Observable<out Effect> {
            return swRepository
                .getHeroesPage(page)
                .map<Effect>(Effect::HeroesIsLoaded)
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(initialEffect)
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is HeroSelected -> state
                is InitialLoading -> state.copy(
                    isLoading = true
                )
                is Loading -> state.copy(
                    isLoadingMore = true
                )
                is HeroesIsLoaded -> state.copy(
                    heroes = state.heroes + effect.heroes.heroes,
                    isLoading = false,
                    isLoadingMore = false,
                    fullData = effect.heroes.isAllHeroesLoaded
                )
                is CurrentPageIncreased -> state.copy(
                    currentPage = state.currentPage + 1
                )
                is SavedHeroesDropped -> state.copy(
                    currentPage = 1,
                    heroes = emptyList()
                )
            }
        }
    }

    class PostProcessorImpl : PostProcessor<Action, Effect, State> {
        override fun invoke(action: Action, effect: Effect, state: State): Action? {
            return when {
                state.isLoadingMore || state.isLoading -> null
                effect is SavedHeroesDropped-> Action.LoadInitialData
                effect is CurrentPageIncreased -> LoadHeroesPage
                else -> null
            }
        }
    }

    class NewsPublisherImpl : NewsPublisher<Action, Effect, State, News> {
        override fun invoke(action: Action, effect: Effect, state: State): News? {
            return when {
                effect is HeroSelected && action is Execute && action.wish is Wish.OpenHeroDetails -> News.HeroSelected(state.heroes.elementAt(action.wish.index))
                else -> null
            }
        }
    }
}
