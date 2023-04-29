package com.arttttt.hero.api

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.ComponentBuilder3
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.heroeslist.api.Hero

interface HeroComponentBuilder : ComponentBuilder3 {

    fun buildComponent(
        componentContext: ComponentContext,
        hero: Hero
    ): DecomposeComponent
}
