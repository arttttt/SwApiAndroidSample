package com.arttttt.swapisamplemvi.ui.heroeslist.bindings

import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.common.ProgressAdapterItem
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListViewModel
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterItem
import com.arttttt.swapisamplemvi.ui.heroeslist.transfromer.HeroesListFragmentEventsTransformer
import com.badoo.mvicore.binder.using

class HeroesListBindings(
    private val heroesListFragmentEventsTransformer: HeroesListFragmentEventsTransformer,
    private val coordinator: RootCoordinator,
    private val heroesFeature: HeroesFeature,
    private val heroFeature: HeroFeature
): BaseBindings<HeroesListFragment>() {
    override fun setup(view: HeroesListFragment) {
        binder.bind(heroesFeature to view using { state ->
            HeroesListViewModel(
                isLoading = state.isInitialLoading,
                items = state
                    .heroes
                    .map { hero -> HeroAdapterItem(hero.name) }
                    .toMutableList<IListItem>()
                    .apply {
                        if (state.isLoadingMore) {
                            add(ProgressAdapterItem)
                        }
                    }
            )
        })
        binder.bind(view to heroesFeature using heroesListFragmentEventsTransformer)
        binder.bind(view to coordinator using { event ->
            when (event) {
                is HeroesListFragment.HeroesListUiAction.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
                else -> null
            }
        })
        binder.bind(heroesFeature.news to heroFeature using { news ->
            when (news) {
                is HeroesFeature.News.HeroSelected -> HeroFeature.Wish.OpenHeroDetails(news.hero)
            }
        })
        binder.bind(heroesFeature.news to coordinator using { news ->
            when (news) {
                is HeroesFeature.News.HeroSelected -> RootCoordinator.RootNavigationEvent.OpenHeroDetails
            }
        })
    }

    override fun clear() {
        heroesFeature.dispose()
    }
}