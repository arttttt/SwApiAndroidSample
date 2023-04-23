package com.arttttt.arch.scope

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.*

interface ScopeOwner {

    companion object {

        fun delegate(lifecycle: Lifecycle): ScopeOwner {
            return object : ScopeOwner {

                override val scope: CoroutineScope = MainScope() + SupervisorJob()

                init {
                    lifecycle.doOnDestroy {
                        scope.coroutineContext.cancelChildren()
                    }
                }
            }
        }
    }

    val scope: CoroutineScope
}
