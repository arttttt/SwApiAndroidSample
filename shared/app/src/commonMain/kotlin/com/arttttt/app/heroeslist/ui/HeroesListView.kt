package com.arttttt.app.heroeslist.ui

import com.arttttt.app.heroeslist.HeroesListComponent
import com.arttttt.arch.view.ComponentView
import com.arttttt.arch.view.ListItem
import kotlinx.collections.immutable.ImmutableList

interface HeroesListView : ComponentView<HeroesListView.Model, HeroesListView.UiEvent> {

    data class Model(
        val items: ImmutableList<ListItem>
    )

    sealed class UiEvent {

        data class HeroClicked(val name: String) : UiEvent()
        object NewPageRequired : UiEvent()
    }
}
