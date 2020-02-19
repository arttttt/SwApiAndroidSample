package com.arttttt.swapisamplemvi.feature.herofeature

import com.arttttt.swapisamplemvi.domain.entity.Hero
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature.*
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature.Effect.HeroInfoLoaded
import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature.Wish.LoadHeroInfo
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class HeroFeature(
    swRepository: SwRepository,
    heroName: String
) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = State(),
    actor = ActorImpl(swRepository),
    reducer = ReducerImpl(),
    bootstrapper = BootStrapperImpl(heroName)
) {

    data class State(
        val hero: Hero? = null
    )

    sealed class Wish {
        class LoadHeroInfo(val name: String): Wish()
    }

    sealed class Effect {
        class HeroInfoLoaded(val hero: Hero): Effect()
    }

    class BootStrapperImpl(private val heroName: String): Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> {
            return Observable.just(LoadHeroInfo(heroName))
        }
    }

    class ActorImpl(
        private val swRepository: SwRepository
    ): Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            is LoadHeroInfo -> swRepository
                .getHeroByName(wish.name)
                .map<Effect>(::HeroInfoLoaded)
                .toObservable()
        }.observeOn(AndroidSchedulers.mainThread())
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is HeroInfoLoaded -> state.copy(hero = effect.hero)
        }
    }
}
