package com.arttttt.heroeslist.impl.ui.toolbar

import com.arttttt.arch.view.ComponentView

internal interface HeroesListToolbarView : ComponentView<HeroesListToolbarView.Model, Unit> {

    data class Model(
        val title: String
    )
}
