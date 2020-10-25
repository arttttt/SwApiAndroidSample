package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.heroeslist.di.HeroesListScope
import com.arttttt.swapisamplemvi.ui.heroeslist.transfromer.HeroesListTransformer
import com.badoo.mvicore.binder.using
import javax.inject.Inject

@HeroesListScope
class HeroesListBindings @Inject constructor(
    private val transformer: HeroesListTransformer,
    private val coordinator: RootCoordinator,
    private val heroesFeature: HeroesFeature,
    private val heroFeature: HeroFeature
): BaseBindings<HeroesListFragment>() {
    override fun setup(view: HeroesListFragment) {
        binder.bind(heroesFeature to view using transformer)
        binder.bind(view.uiActions to heroesFeature using { action ->
            when (action) {
                is HeroesListFragment.Action.HeroClicked -> HeroesFeature.Wish.OpenHeroDetails(action.position)
                is HeroesListFragment.Action.LoadMoreHeroes -> HeroesFeature.Wish.LoadMoreHeroes
                is HeroesListFragment.Action.Refresh -> HeroesFeature.Wish.RefreshHeroes
                else -> null
            }
        })
        binder.bind(view.uiActions to coordinator using { event ->
            when (event) {
                is HeroesListFragment.Action.BackPressed -> RootCoordinator.RootNavigationEvent.BackPressed
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