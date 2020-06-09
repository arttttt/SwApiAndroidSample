package com.arttttt.swapisampleribs.container.rib.heroes_list.mapper

import com.arttttt.swapisampleribs.container.rib.heroes_list.HeroesList.Input
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.Wish

internal object InputToWish : (Input) -> Wish? {

    override fun invoke(event: Input): Wish? =
        TODO("Implement heroesListInputToWish mapping")
}
