package com.arttttt.swapisamplemvi.ui.herodetails

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.AndroidPauseResumeBindings
import com.badoo.mvicore.binder.using

class HeroDetailsBinding(
    lifecycleOwner: LifecycleOwner,
    private val rootCoordinator: RootCoordinator
): AndroidPauseResumeBindings<HeroDetailsViewController>(lifecycleOwner) {
    override fun setup(view: HeroDetailsViewController) {
        binder.bind(view to rootCoordinator using { event ->
            when (event) {
                HeroDetailsViewController.HeroDetailsUiEvent.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
            }
        })
    }
}