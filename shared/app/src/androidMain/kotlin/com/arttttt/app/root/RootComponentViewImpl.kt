package com.arttttt.app.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.view.AndroidAbstractComponentView
import com.arttttt.arch.view.ViewOwner

actual class RootComponentViewImpl actual constructor(
    actual val component: RootComponent,
) : AndroidAbstractComponentView<Unit, Unit>(),
    RootComponentView {

    override val initialState: Unit = Unit

    @Composable
    override fun Content(
        model: Unit,
        modifier: Modifier,
    ) {
        Children(
            stack = component.childStack,
            modifier = modifier,
        ) {
            when (val child = it.instance) {
                is ViewOwner<*> -> child.view.Content(modifier = Modifier)
            }
        }
    }
}
