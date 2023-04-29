package com.arttttt.heroeslist.impl.ui.toolbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arttttt.arch.view.AndroidAbstractComponentView

internal actual class HeroesListToolbarViewImpl actual constructor() :
    AndroidAbstractComponentView<HeroesListToolbarView.Model, Unit>(),
    HeroesListToolbarView {

    override val initialState = HeroesListToolbarView.Model(
        title = "",
    )

    @Composable
    override fun Content(model: HeroesListToolbarView.Model, modifier: Modifier) {
        TopAppBar(
            title = {
                Text(text = model.title)
            },
        )
    }
}
