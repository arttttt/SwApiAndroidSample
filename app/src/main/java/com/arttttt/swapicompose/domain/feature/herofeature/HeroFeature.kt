package com.arttttt.swapicompose.domain.feature.herofeature

import com.arttttt.swapicompose.domain.entity.Hero
import com.arttttt.swapicompose.domain.feature.herofeature.HeroFeature.*
import com.arttttt.swapicompose.domain.feature.herofeature.HeroFeature.News.HeroInformationReceived
import com.arttttt.swapicompose.domain.feature.herofeature.HeroFeature.Wish.OpenHeroDetails
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature

class HeroFeature : ReducerFeature<Wish, State, News>(
    initialState = State(
        hero = null
    ),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val hero: Hero?
    )

    sealed class Wish {
        class OpenHeroDetails(val hero: Hero): Wish()
    }

    sealed class News {
        object HeroInformationReceived: News()
    }

    class ReducerImpl : Reducer<State, Wish> {
        override fun invoke(state: State, wish: Wish): State = when (wish) {
            is OpenHeroDetails -> state.copy(hero = wish.hero)
        }
    }

    class NewsPublisherImpl : SimpleNewsPublisher<Wish, State, News>() {
        override fun invoke(wish: Wish, state: State): News? = when {
            wish is OpenHeroDetails && state.hero != null -> HeroInformationReceived
            else -> null
        }
    }
}
