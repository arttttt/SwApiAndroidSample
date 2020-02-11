package com.arttttt.swapisamplemvi.ui.base

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.lifecycle.ResumePauseBinderLifecycle
import com.badoo.mvicore.binder.Binder

abstract class AndroidPauseResumeBindings<T : ViewController<*, *>>(
    lifecycleOwner: LifecycleOwner
) {
    protected val binder = Binder(
        lifecycle = ResumePauseBinderLifecycle(
            androidLifecycle = lifecycleOwner.lifecycle
        )
    )

    abstract fun setup(view: T)
}