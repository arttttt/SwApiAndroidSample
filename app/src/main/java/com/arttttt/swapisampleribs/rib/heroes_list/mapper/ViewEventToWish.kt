package com.arttttt.swapisampleribs.rib.heroes_list.mapper

import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView.Event
import com.arttttt.swapisampleribs.rib.heroes_list.feature.HeroesListFeature.Wish

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        TODO("Implement heroesListViewEventToWish mapping")
}
