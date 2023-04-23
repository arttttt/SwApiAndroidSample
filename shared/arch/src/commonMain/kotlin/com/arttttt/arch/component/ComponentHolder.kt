package com.arttttt.arch.component

interface ComponentHolder<in C: DecomposeComponent> {

    fun attachComponent(component: C)
}
