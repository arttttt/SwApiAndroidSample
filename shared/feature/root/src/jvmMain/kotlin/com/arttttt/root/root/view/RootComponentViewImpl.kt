package com.arttttt.root.root.view

import com.arttttt.root.root.RootComponent
import kotlinx.coroutines.flow.Flow

actual class RootComponentViewImpl actual constructor(component: RootComponent) :
    RootComponentView {

    override fun render(model: Unit) {
        TODO("Not yet implemented")
    }

    override val events: Flow<Unit>
        get() = TODO("Not yet implemented")

    actual val component: RootComponent
        get() = TODO("Not yet implemented")
}
