package com.arttttt.swapisampleribs.rib.heroes_list.mapper

import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView.ViewModel
import com.arttttt.swapisampleribs.rib.heroes_list.feature.HeroesListFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel {
        return ViewModel(
            items = emptyList()
        )
    }
}
