package com.arttttt.heroeslist.api

import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.ui.HeroesListToolbarView
import com.arttttt.heroeslist.api.ui.list.HeroesListView
import com.arttttt.moduleinjector.BaseFeatureDependencies

interface HeroesListFeatureDependencies : BaseFeatureDependencies {

    val listViewFactory: (HeroesListComponent) -> HeroesListView
    val toolbarViewFactory: () -> HeroesListToolbarView
}
