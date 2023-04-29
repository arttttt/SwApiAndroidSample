package com.arttttt.heroeslist.impl

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.heroeslist.api.HeroesListComponentBuilder

internal class HeroesListComponentBuilderImpl : HeroesListComponentBuilder {

    override fun buildComponent(
        componentContext: ComponentContext
    ): DecomposeComponent {
        return HeroesListComponentImpl(
            componentContext = componentContext,
        )
    }
}
