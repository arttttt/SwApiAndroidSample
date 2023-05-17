package com.arttttt.arch.view

interface ViewOwner<V : ComponentView<*, *>> {

    val view: V
}
