package com.arttttt.swapisamplemvi.ui.heroeslist

import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.feature.heroesfeature.HeroesFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.recyclerview.DefaultDiffCallback
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroAdapterDelegate
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroItemListener
import com.arttttt.swapisamplemvi.utils.extensions.unsafeCastTo
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class HeroesListFragment: BaseFragment<HeroesListFragment.HeroesListUiAction, HeroesListViewModel>(R.layout.fragment_list) {

    sealed class HeroesListUiAction: UiAction {
        object Refresh: HeroesListUiAction()
        object BackPressed: HeroesListUiAction()
        class HeroClicked(val position: Int): HeroesListUiAction()
    }

    override val binder: AndroidBindings<BaseFragment<HeroesListUiAction, HeroesListViewModel>> = HeroesListBinding(
        lifecycleOwner = this,
        coordinator = get(named<RootCoordinator>()),
        heroesFeature = HeroesFeature(
            swRepository = get()
        )
    ).unsafeCastTo()

    private val adapter: ListDifferAdapter by inject {
        parametersOf(
            setOf(
                HeroAdapterDelegate(
                    object: HeroItemListener {
                        override val clicks: Consumer<Int> = Consumer { position ->
                            uiActions.accept(HeroesListUiAction.HeroClicked(position))
                        }
                    }
                )
            ),
            get(named<DefaultDiffCallback>())
        )
    }

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
