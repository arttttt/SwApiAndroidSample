package com.arttttt.root.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arttttt.arch.view.AndroidAbstractComponentView
import com.arttttt.arch.view.ViewOwner
import com.arttttt.heroeslist.api.components.HeroesListComponent
import com.arttttt.root.RootComponent

class RootComponentViewImpl(
    private val component: RootComponent,
) : AndroidAbstractComponentView<Unit, Unit>(), RootComponentView {

    override val initialState: Unit = Unit

    @Composable
    override fun Content(
        model: Unit,
        modifier: Modifier,
    ) {
        Children(
            stack = component.childStack,
            modifier = modifier,
            animation = stackAnimation(
                disableInputDuringAnimation = true,
                animator = fade() + scale(),
            ),
        ) {
            when (val child = it.instance) {
                is ViewOwner<*> -> child.view.Content(modifier = Modifier)
            }
        }
    }
}