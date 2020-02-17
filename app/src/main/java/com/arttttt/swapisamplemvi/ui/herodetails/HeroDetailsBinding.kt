package com.arttttt.swapisamplemvi.ui.herodetails

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using

class HeroDetailsBinding(
    lifecycleOwner: LifecycleOwner,
    private val rootCoordinator: RootCoordinator
): AndroidBindings<HeroDetailsFragment>(lifecycleOwner) {
    override fun setup(view: HeroDetailsFragment) {
        binder.bind(view to rootCoordinator using { event ->
            when (event) {
                HeroDetailsFragment.HeroDetailsUiAction.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
            }
        })
    }
}