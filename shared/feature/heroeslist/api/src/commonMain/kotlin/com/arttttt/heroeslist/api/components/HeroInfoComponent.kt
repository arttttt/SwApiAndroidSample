package com.arttttt.heroeslist.api.components

import com.arttttt.arch.component.DecomposeComponent

interface HeroInfoComponent : DecomposeComponent {

    sealed class Event {
        object Dismissed : Event()
    }

    val title: String
    val message: String

    fun onDismissed()
}
