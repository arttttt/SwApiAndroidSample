package com.arttttt.swapisamplemvi.ui.heroeslist

import android.os.Bundle
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.AppActivity
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.UiActionsDelegate
import com.arttttt.swapisamplemvi.ui.base.recyclerview.DefaultDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.IListItem
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.di.DaggerHeroesListComponent
import com.arttttt.swapisamplemvi.utils.extensions.toObservable
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject
import javax.inject.Provider

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

    @Inject
    lateinit var bindingsProvider: Provider<HeroesListBindings>

    @Inject
    lateinit var uiActionsProvider: Provider<HeroesListUiActionsDelegate>

    @Inject
    lateinit var delegatesProvider: Provider<Set<AdapterDelegate<List<IListItem>>>>

    override fun provideBindings(): BaseBindings<out BaseFragment<Action, ViewModel>> {
        return bindingsProvider.get()
    }

    override fun provideUiActions(): UiActionsDelegate<Action> {
        return uiActionsProvider.get()
    }

    private val adapter by lazy {
        ListDifferAdapter(
            diffCallback = DefaultDiffCallback(),
            delegates = delegatesProvider.get()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerHeroesListComponent.factory().create((requireActivity() as AppActivity).component).inject(this)
        super.onCreate(savedInstanceState)
    }

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
