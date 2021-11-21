package com.arttttt.hero.ui

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arttttt.navigation.BackPressedDispatcher

internal interface HeroController {
    fun onViewCreated(
        view: HeroView,
        viewLifecycle: Lifecycle,
        backPressedDispatcher: BackPressedDispatcher
    )
}
