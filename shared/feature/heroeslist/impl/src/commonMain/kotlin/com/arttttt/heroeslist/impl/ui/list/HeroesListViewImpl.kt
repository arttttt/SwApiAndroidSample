package com.arttttt.heroeslist.impl.ui.list

import com.arttttt.heroeslist.api.HeroesListComponent

internal expect class HeroesListViewImpl(component: HeroesListComponent) : HeroesListView {

    val component: HeroesListComponent
}
