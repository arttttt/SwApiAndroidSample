package com.arttttt.heroeslist.api.ui

import com.arttttt.arch.view.ComponentView

interface HeroesListToolbarView : ComponentView<HeroesListToolbarView.Model, Unit> {

    data class Model(
        val title: String
    )
}