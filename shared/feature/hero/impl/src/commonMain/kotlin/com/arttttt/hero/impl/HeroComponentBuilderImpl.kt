package com.arttttt.hero.impl

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.hero.api.HeroComponentBuilder
import com.arttttt.hero.api.HeroView
import com.arttttt.heroeslist.api.entity.Hero

internal class HeroComponentBuilderImpl(
    private val viewFactory: () -> HeroView,
) : HeroComponentBuilder {

    override fun buildComponent(
        componentContext: ComponentContext,
        hero: Hero,
    ): DecomposeComponent {
        return HeroComponentImpl(
            componentContext = componentContext,
            viewFactory = viewFactory,
            hero = hero,
        )
    }
}
