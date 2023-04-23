package com.arttttt.arch.component

import com.arkivanov.decompose.ComponentContext
import kotlin.reflect.KClass

class ComponentFactoryImpl<in C : Configuration>(
    private val builders: Map<KClass<out C>, ComponentBuilder<*>>
) : ComponentFactory<C> {

    companion object {
        private const val ERROR_MESSAGE = "No builder found for config: %s"
    }

    override fun createComponent(
        configuration: C,
        componentContext: ComponentContext
    ): DecomposeComponent {
        return builders[configuration::class]?.buildComponent(configuration, componentContext) ?: throw IllegalStateException(
            ERROR_MESSAGE.format(configuration))
    }
}
