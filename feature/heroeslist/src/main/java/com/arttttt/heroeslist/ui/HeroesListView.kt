package com.arttttt.heroeslist.ui

import com.arkivanov.mvikotlin.core.view.MviView
import com.arttttt.recyclerview.ListItem

internal interface HeroesListView : MviView<HeroesListView.Model, HeroesListView.UiEvent> {

    data class Model(
        val items: List<ListItem>
    )

    sealed class UiEvent {
        data class HeroClicked(val id: Int) : UiEvent()
        object LoadMore : UiEvent()
    }
}
