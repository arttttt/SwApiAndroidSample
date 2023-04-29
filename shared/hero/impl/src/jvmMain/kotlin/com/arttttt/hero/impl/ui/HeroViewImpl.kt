package com.arttttt.hero.impl.ui

import kotlinx.coroutines.flow.Flow

internal actual class HeroViewImpl : HeroView {

    override fun render(model: HeroView.Model) {
        TODO("Not yet implemented")
    }

    override val events: Flow<HeroView.UiEvent>
        get() = TODO("Not yet implemented")
}
