package com.arttttt.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.view.ViewOwner
import com.arttttt.root.view.RootComponentView

interface RootComponent : DecomposeComponent, ViewOwner<RootComponentView> {

    val childStack: Value<ChildStack<*, DecomposeComponent>>
}
