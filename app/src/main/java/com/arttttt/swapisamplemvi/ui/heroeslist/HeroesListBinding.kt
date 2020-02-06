package com.arttttt.swapisamplemvi.ui.heroeslist

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import timber.log.Timber

class HeroesListBinding(
    lifecycleOwner: LifecycleOwner,
    private val heroesFeature: HeroesFeature
): AndroidBindings<HeroesListView>(lifecycleOwner) {
    override fun setup(view: HeroesListView) {
        binder.bind(heroesFeature to view using { state ->
            HeroesListViewModel(
                isLoading = state.isLoading,
                items = state.heroes.map { hero -> HeroAdapterItem(hero.name) }
            )
        })
        binder.bind(view to heroesFeature using { event ->
            when (event) {
                is HeroesListView.HeroesListUiEvent.Refresh -> HeroesFeature.Wish.RefreshHeroes
                is HeroesListView.HeroesListUiEvent.HeroClicked -> {
                    Timber.e("ignored")
                    null
                }
            }
        })
    }
}