package com.arttttt.heroeslist.impl.ui.list

import com.arttttt.arch.view.PlatformAbstractComponentView
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.ui.list.HeroesListView

abstract class PlatformListComponentView(
    protected val component: HeroesListComponent
) : PlatformAbstractComponentView<HeroesListView.Model, HeroesListView.UiEvent>(),
    HeroesListView