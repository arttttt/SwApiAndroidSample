package com.arttttt.swapisampleribs.extensions

import com.badoo.ribs.core.plugin.NodeLifecycleAware
import com.badoo.ribs.core.plugin.Plugin
import org.koin.core.scope.Scope

val Scope.closeOnNodeDetachPlugin: Plugin
    get() {
        return object: NodeLifecycleAware {
            override fun onDetach() {
                this@closeOnNodeDetachPlugin.close()
            }
        }
    }