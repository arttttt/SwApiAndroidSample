package com.arttttt.swapisampleribs.rib.heroes_list.mapper

import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList.Output
import com.arttttt.swapisampleribs.rib.heroes_list.feature.HeroesListFeature.News

internal object NewsToOutput : (News) -> Output? {

    override fun invoke(news: News): Output? =
        TODO("Implement heroesListNewsToOutput mapping")
}
