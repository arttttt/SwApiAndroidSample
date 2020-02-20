package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.recyclerview.DefaultDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroItemListener
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class HeroesListFragment: BaseFragment<HeroesListFragment.HeroesListUiAction, HeroesListViewModel>(R.layout.fragment_list) {

    sealed class HeroesListUiAction: UiAction {
        object Refresh: HeroesListUiAction()
        object BackPressed: HeroesListUiAction()
        class HeroClicked(val position: Int): HeroesListUiAction()
    }

    @Inject
    override lateinit var binder: AndroidBindings<BaseFragment<HeroesListUiAction, HeroesListViewModel>>

    private val adapter = ListDifferAdapter(
        DefaultDiffCallback(),
        setOf(
            HeroAdapterDelegate(
                object: HeroItemListener {
                    override val clicks: Consumer<Int> = Consumer { position ->
                        uiActions.accept(HeroesListUiAction.HeroClicked(position))
                    }
                }
            )
        )
    )

    override fun onViewCreated() {
        super.onViewCreated()

        refreshLayout
            .refreshes()
            .map { HeroesListUiAction.Refresh }
            .emitUiAction()

        rvHeroes.adapter = adapter

        val watcher = modelWatcher<HeroesListViewModel> {
            watch(HeroesListViewModel::isLoading, byValue(), refreshLayout::setRefreshing)
            watch(HeroesListViewModel::items, byValue(), adapter::setItems)
        }

        states
            .subscribe(watcher::invoke)
            .add()
    }

    override fun onBackPressed() {
        Observable
            .just(HeroesListUiAction.BackPressed)
            .emitUiAction()
    }
}
