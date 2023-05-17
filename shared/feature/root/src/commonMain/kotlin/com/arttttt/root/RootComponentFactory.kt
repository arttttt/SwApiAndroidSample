package com.arttttt.root

import com.arkivanov.decompose.ComponentContext
import com.arttttt.arch.component.ComponentFactory
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.hero.api.HeroComponentBuilder
import com.arttttt.heroeslist.api.HeroesListComponentBuilder

internal class RootComponentFactory(
    private val heroesListComponentBuilder: HeroesListComponentBuilder,
    private val heroComponentBuilder: HeroComponentBuilder,
) : ComponentFactory<RootComponentImpl.Screen> {

    override fun createComponent(
        configuration: RootComponentImpl.Screen,
        componentContext: ComponentContext
    ): DecomposeComponent {
        return when (configuration) {
            is RootComponentImpl.Screen.HeroesList -> {
                heroesListComponentBuilder.buildComponent(componentContext)
            }
            is RootComponentImpl.Screen.Hero -> {
                heroComponentBuilder.buildComponent(
                    componentContext = componentContext,
                    hero = configuration.hero,
                )
            }
        }
    }
}
