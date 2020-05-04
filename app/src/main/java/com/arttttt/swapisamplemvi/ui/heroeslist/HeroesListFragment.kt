package com.arttttt.swapisamplemvi.ui.heroeslist

import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.utils.extensions.toObservable
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HeroesListFragment: BaseFragment<HeroesListFragment.Action, HeroesListFragment.ViewModel>(R.layout.fragment_list) {

    sealed class Action: UiAction {
        object Refresh: Action()
        object BackPressed: Action()
        object LoadMoreHeroes: Action()
        class HeroClicked(val position: Int): Action()
    }

    data class ViewModel(
        val isLoading: Boolean,
        val items: List<IListItem>
    ): com.arttttt.swapisamplemvi.ui.base.ViewModel

    private val adapter: ListDifferAdapter by inject { parametersOf(scope.get<DiffUtil.ItemCallback<*>>(), scope.get<Set<AdapterDelegate<*>>>()) }

    override fun onViewCreated() {
        super.onViewCreated()

        adapter
            .loadMoreObservable
            .map { Action.LoadMoreHeroes }
            .bindUiAction()

        refreshLayout
            .refreshes()
            .map { Action.Refresh }
            .bindUiAction()

        rvHeroes.adapter = adapter
    }

    override fun onBackPressed() {
        Action.BackPressed
            .toObservable()
            .bindUiAction()
    }

    override fun getModelWatcher(): ModelWatcher<ViewModel> {
        return modelWatcher {
            watch(ViewModel::isLoading, byValue(), refreshLayout::setRefreshing)
            watch(ViewModel::items, byValue(), adapter::setItems)
        }
    }
}
