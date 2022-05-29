package com.arttttt.heroeslist.ui.list.controller

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arttttt.heroeslist.ui.list.view.HeroesListView

internal interface HeroesListController {

    fun onViewCreated(view: HeroesListView, lifecycle: Lifecycle)
}
