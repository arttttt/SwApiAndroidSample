package com.arttttt.app.heroeslist.ui

import com.arttttt.arch.component.DecomposeComponent
import kotlinx.coroutines.flow.Flow

actual class HeroesListViewImpl : HeroesListView {

    override fun render(model: HeroesListView.Model) {
        TODO("Not yet implemented")
    }

    override val events: Flow<HeroesListView.UiEvent>
        get() = TODO("Not yet implemented")

    override fun attachComponent(component: DecomposeComponent) {
        TODO("Not yet implemented")
    }
}
