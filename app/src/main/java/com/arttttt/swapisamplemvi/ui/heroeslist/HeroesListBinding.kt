package com.arttttt.swapisamplemvi.ui.heroeslist

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import timber.log.Timber

class HeroesListBinding(
    lifecycleOwner: LifecycleOwner,
    private val coordinator: RootCoordinator,
    private val heroesFeature: HeroesFeature
): AndroidBindings<HeroesListViewController>(lifecycleOwner) {
    override fun setup(view: HeroesListViewController) {
        binder.bind(heroesFeature to view using { state ->
            HeroesListViewModel(
                isLoading = state.isLoading,
                items = state.heroes.map { hero -> HeroAdapterItem(hero.name) }
            )
        })
        binder.bind(view to heroesFeature using { event ->
            when (event) {
                is HeroesListViewController.HeroesListUiEvent.Refresh -> HeroesFeature.Wish.RefreshHeroes
                is HeroesListViewController.HeroesListUiEvent.HeroClicked -> null
                is HeroesListViewController.HeroesListUiEvent.BackPressed -> null
            }
        })
        binder.bind(view to coordinator using { event ->
            when (event) {
                is HeroesListViewController.HeroesListUiEvent.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
                is HeroesListViewController.HeroesListUiEvent.HeroClicked -> RootCoordinator.RootNavigationEvent.OpenHeroDetails
                else -> null
            }
        })
    }
}