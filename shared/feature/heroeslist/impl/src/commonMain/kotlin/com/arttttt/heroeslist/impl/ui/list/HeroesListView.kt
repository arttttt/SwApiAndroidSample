package com.arttttt.heroeslist.impl.ui.list

import com.arttttt.arch.view.ComponentView
import com.arttttt.arch.view.ListItem
import kotlinx.collections.immutable.ImmutableList

internal interface HeroesListView : ComponentView<HeroesListView.Model, HeroesListView.UiEvent> {

    data class Model(
        val items: ImmutableList<ListItem>
    )

    sealed class UiEvent {

        data class HeroClicked(val name: String) : UiEvent()
        object NewPageRequired : UiEvent()
    }
}
