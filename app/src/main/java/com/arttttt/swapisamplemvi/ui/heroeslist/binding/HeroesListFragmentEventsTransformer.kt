package com.arttttt.swapisamplemvi.ui.heroeslist.binding

import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.badoo.mvicore.connector.Connector
import io.reactivex.Observable
import io.reactivex.ObservableSource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HeroesListFragmentEventsTransformer @Inject constructor(): Connector<HeroesListFragment.HeroesListUiAction, HeroesFeature.Wish> {
    override fun invoke(element: ObservableSource<out HeroesListFragment.HeroesListUiAction>): ObservableSource<HeroesFeature.Wish> {
        return Observable
            .wrap(element)
            .throttleFirst(100, TimeUnit.MILLISECONDS)
            .flatMap {
                map(it)
                    ?.let { Observable.just(it) }
                    ?: Observable.empty()
            }
    }

    private fun map(action: HeroesListFragment.HeroesListUiAction): HeroesFeature.Wish? {
        return when (action) {
            is HeroesListFragment.HeroesListUiAction.Refresh -> HeroesFeature.Wish.RefreshHeroes
            is HeroesListFragment.HeroesListUiAction.HeroClicked -> HeroesFeature.Wish.OpenHeroDetails(action.position)
            is HeroesListFragment.HeroesListUiAction.BackPressed -> null
            is HeroesListFragment.HeroesListUiAction.LoadMoreHeroes -> HeroesFeature.Wish.LoadMoreHeroes
        }
    }
}