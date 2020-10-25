package com.arttttt.swapisamplemvi.ui.heroeslist.transfromer

import com.arttttt.swapisamplemvi.domain.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.base.extensions.addLoadingMore
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.heroeslist.HeroesListFragment
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.models.HeroAdapterItem
import com.arttttt.swapisamplemvi.ui.heroeslist.di.HeroesListScope
import com.arttttt.swapisamplemvi.utils.extensions.invokeIf
import com.arttttt.swapisamplemvi.utils.extensions.wrap
import com.badoo.mvicore.connector.Connector
import io.reactivex.ObservableSource
import javax.inject.Inject

@HeroesListScope
class HeroesListTransformer @Inject constructor(): Connector<HeroesFeature.State, HeroesListFragment.ViewModel> {
    override fun invoke(states: ObservableSource<out HeroesFeature.State>): ObservableSource<HeroesListFragment.ViewModel> {
        return states
            .wrap()
            .map { state ->
                HeroesListFragment.ViewModel(
                    isLoading = state.isLoading,
                    items = createItems(state)
                )
            }
    }

    private fun createItems(state: HeroesFeature.State): List<IListItem> {
        val items = mutableListOf<IListItem>()

        state.heroes.mapTo(items) { hero ->
            HeroAdapterItem(
                hero.name
            )
        }

        invokeIf(state::isLoadingMore, items::addLoadingMore)

        return items
    }
}