package com.arttttt.swapisamplemvi.feature.heroesfeature

import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.Serializable

class HeroesFeature(
    swRepository: SwRepository
): ActorReducerFeature<HeroesFeature.Wish, HeroesFeature.Effect, HeroesFeature.State, Nothing>(
    initialState = State(
        isLoading = false,
        heroes = emptyList()
    ),
    reducer = ReducerImpl(),
    actor = ActorImpl(swRepository),
    bootstrapper = BootstrapperImpl()
) {

    data class State(
        val isLoading: Boolean,
        val heroes: List<Hero>
    ): Serializable

    sealed class Wish {
        object LoadHeroes: Wish()
        object RefreshHeroes: Wish()
    }

    sealed class Effect {
        object Loading: Effect()
        class HeroesLoaded(val heroes: List<Hero>): Effect()
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
            }
        }
    }

    class ActorImpl(
        private val swRepository: SwRepository
    ): Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<out Effect> {
            return when (wish) {
                Wish.LoadHeroes -> {
                    swRepository
                        .getHeroes()
                        .map<Effect>(Effect::HeroesLoaded)
                        .startWith(Observable.just(Effect.Loading))
                        .observeOn(AndroidSchedulers.mainThread())
                }
                Wish.RefreshHeroes -> {
                    swRepository
                        .deleteAllHeroes()
                        .andThen(swRepository.getHeroes())
                        .map<Effect>(Effect::HeroesLoaded)
                        .startWith(Observable.just(Effect.Loading))
                        .observeOn(AndroidSchedulers.mainThread())
                }
            }
        }
    }
}