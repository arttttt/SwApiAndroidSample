package com.arttttt.swapisamplemvi.ui.heroeslist

import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.DiffUtil
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.ui.base.BaseBindings
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroItemListener
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HeroesListFragment: BaseFragment<HeroesListFragment.HeroesListUiAction, HeroesListViewModel>(R.layout.fragment_list), HeroItemListener {

    sealed class HeroesListUiAction: UiAction {
        object Refresh: HeroesListUiAction()
        object BackPressed: HeroesListUiAction()
        object LoadMoreHeroes: HeroesListUiAction()
        class HeroClicked(val position: Int): HeroesListUiAction()
    }

    private val adapter: ListDifferAdapter by inject { parametersOf(scope.get<DiffUtil.ItemCallback<*>>(), scope.get<Set<AdapterDelegate<*>>>() { parametersOf(this) }) }

    override val clicks: Consumer<Int> = Consumer { position ->
        uiActions.accept(HeroesListUiAction.HeroClicked(position))
    }

    override fun onViewPreCreated() {
        super.onViewPreCreated()

        postponeEnterTransition()
        requireView().doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onViewCreated() {
        super.onViewCreated()

        adapter
            .loadMoreObservable
            .map { HeroesListUiAction.LoadMoreHeroes }
            .bindUiAction()

        refreshLayout
            .refreshes()
            .map { HeroesListUiAction.Refresh }
            .bindUiAction()

        rvHeroes.adapter = adapter
    }

    override fun onBackPressed() {
        Observable
            .just(HeroesListUiAction.BackPressed)
            .bindUiAction()
    }

    override fun getModelWatcher(): ModelWatcher<HeroesListViewModel> {
        return modelWatcher {
            watch(HeroesListViewModel::isLoading, byValue(), refreshLayout::setRefreshing)
            watch(HeroesListViewModel::items, byValue(), adapter::setItems)
        }
    }
}
