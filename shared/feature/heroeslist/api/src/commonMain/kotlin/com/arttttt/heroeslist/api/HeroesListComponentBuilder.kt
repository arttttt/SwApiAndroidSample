package com.arttttt.heroeslist.api

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.ComponentBuilder3
import com.arttttt.arch.component.DecomposeComponent

interface HeroesListComponentBuilder : ComponentBuilder3 {

    fun buildComponent(
        componentContext: ComponentContext
    ): DecomposeComponent
}
