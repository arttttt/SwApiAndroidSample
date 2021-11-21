package com.arttttt.hero.ui

import com.arkivanov.mvikotlin.core.view.MviView
import com.arttttt.recyclerview.ListItem

internal interface HeroView : MviView<HeroView.Model, HeroView.UiEvent> {

    data class Model(
        val title: String,
        val items: List<ListItem>
    )

    sealed class UiEvent {
        object BackPressed : UiEvent()
    }

}
