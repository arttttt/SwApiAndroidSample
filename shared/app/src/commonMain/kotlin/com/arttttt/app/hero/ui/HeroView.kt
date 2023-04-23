package com.arttttt.app.hero.ui

import com.arttttt.arch.view.ComponentView

interface HeroView : ComponentView<HeroView.Model, HeroView.UiEvent> {

    data class Model(
        val title: String,
    )

    sealed class UiEvent {

        object BackPressed : UiEvent()
    }
}
