package com.arttttt.swapisamplemvi.ui.heroeslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.MviView
import com.arttttt.swapisamplemvi.ui.base.UiEvent
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class HeroesListView: MviView<HeroesListView.HeroesListUiEvent, HeroesListViewModel>() {

    sealed class HeroesListUiEvent: UiEvent {
        object Refresh: HeroesListUiEvent()
    }

    override val layoutRes: Int = R.layout.fragment_list

    private val adapter: ListDifferAdapter by inject { parametersOf(setOf(HeroAdapterDelegate()), null) }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).apply {
            refreshes()
                .map { HeroesListUiEvent.Refresh }
                .emitUiEvent()
        }

        view.findViewById<RecyclerView>(R.id.rvHeroes).apply {
            this.adapter = this@HeroesListView.adapter
        }

        val watcher = modelWatcher<HeroesListViewModel> {
            watch(HeroesListViewModel::isLoading, byValue(), refreshLayout::setRefreshing)
            watch(HeroesListViewModel::items, byValue(), adapter::setItems)
        }

        states
            .subscribe(watcher::invoke)
            .putToBag()
    }
}