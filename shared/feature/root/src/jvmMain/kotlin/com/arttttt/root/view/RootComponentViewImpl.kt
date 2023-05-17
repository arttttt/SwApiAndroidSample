package com.arttttt.root.view

import com.arttttt.root.RootComponent
import kotlinx.coroutines.flow.Flow

actual class RootComponentViewImpl actual constructor(
    actual val component: RootComponent
) : RootComponentView {

    override fun render(model: Unit) {
        TODO("Not yet implemented")
    }

    override val events: Flow<Unit>
        get() = TODO("Not yet implemented")
}