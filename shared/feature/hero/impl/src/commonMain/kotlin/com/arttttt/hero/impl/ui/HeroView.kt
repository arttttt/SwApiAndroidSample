package com.arttttt.hero.impl.ui

import com.arttttt.arch.view.ComponentView

internal interface HeroView : ComponentView<HeroView.Model, HeroView.UiEvent> {

    data class Model(
        val title: String,
    )

    sealed class UiEvent {

        object BackPressed : UiEvent()
    }
}