package com.arttttt.swapisamplemvi.ui.herodetails

import com.arttttt.swapisamplemvi.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.lifecycle.SimpleFragmentLifecycleOwner
import com.badoo.mvicore.binder.using

class HeroDetailsBindings(
    lifecycleOwner: SimpleFragmentLifecycleOwner,
    private val rootCoordinator: RootCoordinator,
    private val heroFeature: HeroFeature
): BaseBindings<HeroDetailsFragment>(lifecycleOwner) {
    override fun setup(view: HeroDetailsFragment) {
        binder.bind(heroFeature to view using { state ->
            HeroDetailsViewModel(
                name = state.hero?.name ?: "",
                birthDate = state.hero?.birthYear ?: ""
            )
        })

        binder.bind(view to heroFeature using { event ->
            when (event) {
                is HeroDetailsFragment.HeroDetailsUiAction.BackPressed -> null
            }
        })

        binder.bind(view to rootCoordinator using { event ->
            when (event) {
                is HeroDetailsFragment.HeroDetailsUiAction.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
            }
        })
    }
}