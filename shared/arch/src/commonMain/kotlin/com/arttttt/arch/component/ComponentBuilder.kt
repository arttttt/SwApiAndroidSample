package com.arttttt.arch.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelable

interface ComponentBuilder<out Component: DecomposeComponent> {

    @Suppress("UNCHECKED_CAST")
    companion object {

        operator fun <Configuration: Parcelable, Component: DecomposeComponent> invoke(
            block: (Configuration, ComponentContext) -> Component
        ): ComponentBuilder<Component> {
            return object : ComponentBuilder<Component> {
                override fun <Config : Parcelable> buildComponent(
                    config: Config,
                    componentContext: ComponentContext
                ): Component {
                    return block.invoke(config as Configuration, componentContext)
                }
            }
        }
    }

    fun <Config: Parcelable> buildComponent(config: Config, componentContext: ComponentContext): Component
}
