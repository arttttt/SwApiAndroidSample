package com.arttttt.swapisamplemvi.ui.herodetails

import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.herodetails.di.HeroDetailScope
import com.badoo.mvicore.binder.using
import javax.inject.Inject

@HeroDetailScope
class HeroDetailsBindings @Inject constructor(
    private val rootCoordinator: RootCoordinator,
    private val heroFeature: HeroFeature
): BaseBindings<HeroDetailsFragment>() {
    override fun setup(view: HeroDetailsFragment) {
        binder.bind(heroFeature to view using { state ->
            HeroDetailsFragment.ViewModel(
                name = state.hero?.name ?: "",
                birthDate = state.hero?.birthYear ?: ""
            )
        })

        binder.bind(view.uiActions to heroFeature using { event ->
            when (event) {
                is HeroDetailsFragment.Action.BackPressed -> null
            }
        })

        binder.bind(view.uiActions to rootCoordinator using { event ->
            when (event) {
                is HeroDetailsFragment.Action.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
            }
        })
    }

    override fun clear() {
        heroFeature.dispose()
    }
}