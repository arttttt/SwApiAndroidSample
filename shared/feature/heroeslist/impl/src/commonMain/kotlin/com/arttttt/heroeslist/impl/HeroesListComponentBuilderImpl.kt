package com.arttttt.heroeslist.impl

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.heroeslist.api.HeroesListComponentBuilder
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.ui.HeroesListToolbarView
import com.arttttt.heroeslist.api.ui.list.HeroesListView
import com.arttttt.heroeslist.impl.components.list.HeroesListComponentImpl

internal class HeroesListComponentBuilderImpl(
    private val listViewFactory: (HeroesListComponent) -> HeroesListView,
    private val toolbarViewFactory: () -> HeroesListToolbarView,
) : HeroesListComponentBuilder {

    override fun buildComponent(
        componentContext: ComponentContext
    ): DecomposeComponent {
        return HeroesListComponentImpl(
            componentContext = componentContext,
            listViewFactory = listViewFactory,
            toolbarViewFactory = toolbarViewFactory,
        )
    }
}
