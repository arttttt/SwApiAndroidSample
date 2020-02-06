package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.MviView
import com.arttttt.swapisamplemvi.ui.base.UiEvent
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroItemListener
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class HeroesListView: MviView<HeroesListView.HeroesListUiEvent, HeroesListViewModel>() {

    sealed class HeroesListUiEvent: UiEvent {
        object Refresh: HeroesListUiEvent()
        object HeroClicked: HeroesListUiEvent()
    }

    override val layoutRes: Int = R.layout.fragment_list

    private val adapter: ListDifferAdapter by inject {
        parametersOf(
            setOf(
                HeroAdapterDelegate(
                    object: HeroItemListener {
                        override val clicks: Consumer<Unit> = Consumer {
                            uiEvents.accept(HeroesListUiEvent.HeroClicked)
                        }
                    }
                )
            ),
            null
        )
    }

    override fun onViewCreated() {
        super.onViewCreated()

        refreshLayout
            .refreshes()
            .map { HeroesListUiEvent.Refresh }
            .emitUiEvent()

        rvHeroes.adapter = adapter

        val watcher = modelWatcher<HeroesListViewModel> {
            watch(HeroesListViewModel::isLoading, byValue(), refreshLayout::setRefreshing)
            watch(HeroesListViewModel::items, byValue(), adapter::setItems)
        }

        states
            .subscribe(watcher::invoke)
            .putToBag()
    }
}