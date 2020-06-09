package com.arttttt.swapisampleribs.container.rib.heroes_list.mapper

import com.arttttt.swapisampleribs.container.rib.heroes_list.heroesListView.ViewModel
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel {
        return ViewModel(
            i = 1
        )
    }
}
