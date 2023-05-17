package com.arttttt.heroeslist.impl.ui.list

import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.heroeslist.api.ui.list.HeroesListView
import kotlinx.coroutines.flow.Flow

internal actual class HeroesListViewImpl actual constructor(component: HeroesListComponent) :
    HeroesListView {

    override fun render(model: HeroesListView.Model) {
        TODO("Not yet implemented")
    }

    override val events: Flow<HeroesListView.UiEvent>
        get() = TODO("Not yet implemented")

    actual val component: HeroesListComponent
        get() = TODO("Not yet implemented")
}
