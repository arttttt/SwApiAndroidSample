package com.arttttt.app.hero.ui

import com.arttttt.app.hero.HeroComponent
import kotlinx.coroutines.flow.Flow

actual class HeroViewImpl : HeroView {

    override fun render(model: HeroView.Model) {
        TODO("Not yet implemented")
    }

    override fun attachComponent(component: HeroComponent) {
        TODO("Not yet implemented")
    }

    override val events: Flow<HeroView.UiEvent>
        get() = TODO("Not yet implemented")
}
