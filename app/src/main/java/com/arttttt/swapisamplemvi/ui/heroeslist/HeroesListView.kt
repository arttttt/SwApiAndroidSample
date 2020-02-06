package com.arttttt.swapisamplemvi.ui.heroeslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.MviView
import com.arttttt.swapisamplemvi.ui.base.UiEvent
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes

class HeroesListView: MviView<HeroesListView.HeroesListUiEvent, HeroesListViewModel>() {

    sealed class HeroesListUiEvent: UiEvent {
        object Refresh: HeroesListUiEvent()
    }

    override val layoutRes: Int = R.layout.fragment_list

    private val adapter = ListDelegationAdapter<List<IListItem>>(HeroAdapterDelegate())

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        view.findViewById<RecyclerView>(R.id.rvHeroes).let { recyclerView ->
            recyclerView.adapter = adapter

            states
                .map(HeroesListViewModel::items)
                .subscribe { items ->
                    adapter.items = items
                    adapter.notifyDataSetChanged()
                }
                .putToBag()
        }

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).let { swipeRefreshLayout ->
            states
                .map(HeroesListViewModel::isLoading)
                .subscribe(swipeRefreshLayout::setRefreshing)
                .putToBag()

            swipeRefreshLayout
                .refreshes()
                .map { HeroesListUiEvent.Refresh }
                .emitUiEvent()
        }
    }
}