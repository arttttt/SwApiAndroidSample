package com.arttttt.swapisamplemvi.ui.heroeslist

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using

class HeroesListBinding(
    lifecycleOwner: LifecycleOwner,
    private val coordinator: RootCoordinator,
    private val heroesFeature: HeroesFeature
): AndroidBindings<HeroesListFragment>(lifecycleOwner) {
    override fun setup(view: HeroesListFragment) {
        binder.bind(heroesFeature to view using { state ->
            HeroesListViewModel(
                isLoading = state.isLoading,
                items = state.heroes.map { hero -> HeroAdapterItem(hero.name) }
            )
        })
        binder.bind(view to heroesFeature using { event ->
            when (event) {
                is HeroesListFragment.HeroesListUiAction.Refresh -> HeroesFeature.Wish.RefreshHeroes
                is HeroesListFragment.HeroesListUiAction.HeroClicked -> null
                is HeroesListFragment.HeroesListUiAction.BackPressed -> null
            }
        })
        binder.bind(view to coordinator using { event ->
            when (event) {
                is HeroesListFragment.HeroesListUiAction.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
                is HeroesListFragment.HeroesListUiAction.HeroClicked -> RootCoordinator.RootNavigationEvent.OpenHeroDetails
                else -> null
            }
        })
    }
}