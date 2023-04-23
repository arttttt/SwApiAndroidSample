package com.arttttt.arch.view

interface ViewOwner<V: ComponentView<*, *>> {

    companion object {

        fun <V : ComponentView<*, *>> create(
            viewFactory: () -> V,
        ): ViewOwner<V> {
            return object : ViewOwner<V> {

                override val view: V by lazy {
                    viewFactory.invoke()
                }
            }
        }
    }

    val view: V
}
