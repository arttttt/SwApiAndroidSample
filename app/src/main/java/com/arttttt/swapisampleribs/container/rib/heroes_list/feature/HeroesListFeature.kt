package com.arttttt.swapisampleribs.container.rib.heroes_list.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.Effect
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.News
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.State
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.Wish
import io.reactivex.Observable
import io.reactivex.Observable.empty

internal class HeroesListFeature : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State(),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val yourData: Any? = null
    )

    sealed class Wish

    sealed class Effect

    sealed class News

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> =
            empty()
    }

    class ActorImpl : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> =
            empty()
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            state
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? =
            null
    }
}
