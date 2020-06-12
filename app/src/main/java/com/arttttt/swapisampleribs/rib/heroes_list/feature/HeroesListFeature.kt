package com.arttttt.swapisampleribs.rib.heroes_list.feature

import com.arttttt.swapisampleribs.domain.entity.Hero
import com.arttttt.swapisampleribs.domain.repository.HeroesRepository
import com.arttttt.swapisampleribs.rib.heroes_list.feature.HeroesListFeature.*
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

internal class HeroesListFeature(
    heroesRepository: HeroesRepository
) : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State(
        heroes = emptyList()
    ),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(
        heroesRepository = heroesRepository
    ),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val heroes: List<Hero>
    )

    sealed class Wish {
        object LoadHeroes: Wish()
    }

    sealed class Effect {
        class HeroesLoaded(val heroes: List<Hero>): Effect()
    }

    sealed class News

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> {
            return Observable.just(Wish.LoadHeroes)
        }
    }

    class ActorImpl(
        private val heroesRepository: HeroesRepository
    ) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, wish: Wish): Observable<out Effect> {
            return when (wish) {
                is Wish.LoadHeroes -> heroesRepository
                    .loadHeroes()
                    .map(Effect::HeroesLoaded)
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.HeroesLoaded -> state.copy(
                    heroes = effect.heroes
                )
            }
        }
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? {
            return null
        }
    }
}
