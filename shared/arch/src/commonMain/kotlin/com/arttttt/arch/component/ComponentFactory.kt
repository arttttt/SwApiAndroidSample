package com.arttttt.arch.component

import com.arkivanov.decompose.ComponentContext

interface ComponentFactory<in C: Configuration> {

    fun createComponent(configuration: C, componentContext: ComponentContext): DecomposeComponent
}
