package com.arttttt.swapisamplemvi.ui.base

import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycle
import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycleOwner
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.lifecycle.Lifecycle

abstract class BaseBindings<T : Any>(
    simpleFragmentLifecycleOwner: SimpleFragmentLifecycleOwner
) {

    private val manualLifecycle = Lifecycle.manual()
    protected val binder = Binder(
        lifecycle = manualLifecycle
    )

    init {
        simpleFragmentLifecycleOwner.lifecycleListener = object: SimpleFragmentLifecycle {
            override fun onViewCreated() {
                manualLifecycle.begin()
            }

            override fun onViewDestroyed() {
                manualLifecycle.end()
            }
        }
    }

    abstract fun setup(view: T)
}