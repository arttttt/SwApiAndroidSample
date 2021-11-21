package com.arttttt.heroeslist.ui

import com.arkivanov.essenty.lifecycle.Lifecycle

internal interface HeroesListController {
    fun onViewCreated(
        view: HeroesListView,
        viewLifecycle: Lifecycle
    )
}
