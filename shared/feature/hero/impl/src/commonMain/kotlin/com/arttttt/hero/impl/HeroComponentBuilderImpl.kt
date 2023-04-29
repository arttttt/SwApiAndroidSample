package com.arttttt.hero.impl

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.hero.api.HeroComponentBuilder
import com.arttttt.heroeslist.api.Hero

internal class HeroComponentBuilderImpl : HeroComponentBuilder {

    override fun buildComponent(
        componentContext: ComponentContext,
        hero: Hero,
    ): DecomposeComponent {
        return HeroComponentImpl(
            componentContext = componentContext,
            hero = hero,
        )
    }
}
