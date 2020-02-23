package com.arttttt.swapisamplemvi.ui.heroeslist.binding

import androidx.lifecycle.LifecycleOwner
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListViewModel
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using

class HeroesListBinding(
    lifecycleOwner: LifecycleOwner,
    private val heroesListFragmentEventsTransformer: HeroesListFragmentEventsTransformer,
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
        binder.bind(view to heroesFeature using heroesListFragmentEventsTransformer)
        binder.bind(view to coordinator using { event ->
            when (event) {
                is HeroesListFragment.HeroesListUiAction.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
                else -> null
            }
        })
        binder.bind(heroesFeature.news to coordinator using { news ->
            when (news) {
                is HeroesFeature.News.HeroSelected -> RootCoordinator.RootNavigationEvent.OpenHeroDetails(news.name)
            }
        })
    }
}