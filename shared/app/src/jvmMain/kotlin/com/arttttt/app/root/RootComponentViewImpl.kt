package com.arttttt.app.root

import kotlinx.coroutines.flow.Flow

actual class RootComponentViewImpl : RootComponentView {

    override fun render(model: RootComponentView.Model) {
        TODO("Not yet implemented")
    }

    override val events: Flow<RootComponentView.UiEvent>
        get() = TODO("Not yet implemented")
}
