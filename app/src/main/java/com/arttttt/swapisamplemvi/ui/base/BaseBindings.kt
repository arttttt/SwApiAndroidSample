package com.arttttt.swapisamplemvi.ui.base

import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.lifecycle.Lifecycle

abstract class BaseBindings<T: Any>: SimpleFragmentLifecycle {

    private val manualLifecycle = Lifecycle.manual()
    protected val binder = Binder(
        lifecycle = manualLifecycle
    )

    abstract fun setup(view: T)

    abstract fun clear()

    override fun onViewCreated() {
        manualLifecycle.begin()
    }

    override fun onViewDestroyed() {
        manualLifecycle.end()
    }

    override fun onDestroy() {
        clear()
    }
}