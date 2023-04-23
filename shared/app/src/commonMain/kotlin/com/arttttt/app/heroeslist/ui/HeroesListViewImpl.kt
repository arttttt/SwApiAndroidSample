package com.arttttt.app.heroeslist.ui

import com.arttttt.app.heroeslist.HeroesListComponent

expect class HeroesListViewImpl(component: HeroesListComponent) : HeroesListView {

    val component: HeroesListComponent
}
