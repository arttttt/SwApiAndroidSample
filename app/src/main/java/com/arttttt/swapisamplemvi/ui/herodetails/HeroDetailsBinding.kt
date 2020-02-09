package com.arttttt.swapisamplemvi.ui.herodetails

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using

class HeroDetailsBinding(
    lifecycleOwner: LifecycleOwner,
    private val rootCoordinator: RootCoordinator
): AndroidBindings<HeroDetailsViewController>(lifecycleOwner) {
    override fun setup(view: HeroDetailsViewController) {
        binder.bind(view to rootCoordinator using { event ->
            when (event) {
                HeroDetailsViewController.HeroDetailsUiEvent.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
            }
        })
    }
}