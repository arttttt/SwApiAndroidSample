package com.arttttt.app.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arttttt.arch.component.DecomposeComponent
import com.arttttt.arch.view.ViewOwner

interface RootComponent : DecomposeComponent, ViewOwner<RootComponentView> {

    val childStack: Value<ChildStack<*, DecomposeComponent>>
}
