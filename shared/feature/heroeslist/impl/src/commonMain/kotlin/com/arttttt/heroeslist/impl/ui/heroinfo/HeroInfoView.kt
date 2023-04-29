package com.arttttt.heroeslist.impl.ui.heroinfo

import com.arttttt.arch.view.ComponentView

internal interface HeroInfoView : ComponentView<HeroInfoView.Model, HeroInfoView.UiEvent> {

    data class Model(
        val title: String,
        val message: String,
    )

    sealed class UiEvent {

        object Dismissed : UiEvent()
    }
}
