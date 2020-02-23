package com.arttttt.swapisamplemvi.ui.heroeslist

import android.content.Context
import android.view.View
import androidx.core.app.SharedElementCallback
import com.arttttt.swapisamplemvi.R
import com.arttttt.swapisamplemvi.di.factories.ListDifferAdapterFactory
import com.arttttt.swapisamplemvi.ui.base.BaseFragment
import com.arttttt.swapisamplemvi.ui.base.SharedElementProvider
import com.arttttt.swapisamplemvi.ui.base.UiAction
import com.arttttt.swapisamplemvi.ui.base.recyclerview.ListDifferAdapter
import com.arttttt.swapisamplemvi.ui.heroeslist.adapter.HeroItemListener
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.byValue
import com.badoo.mvicore.modelWatcher
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_hero.view.*
import javax.inject.Inject

class HeroesListFragment: BaseFragment<HeroesListFragment.HeroesListUiAction, HeroesListViewModel>(R.layout.fragment_list),
    SharedElementProvider,
    HeroItemListener {

    sealed class HeroesListUiAction: UiAction {
        object Refresh: HeroesListUiAction()
        object BackPressed: HeroesListUiAction()
        object LoadMoreHeroes: HeroesListUiAction()
        class HeroClicked(val position: Int): HeroesListUiAction()
    }

    @Inject
    override lateinit var binder: AndroidBindings<BaseFragment<HeroesListUiAction, HeroesListViewModel>>

    @Inject
    protected lateinit var adapterFactory: ListDifferAdapterFactory

    private val adapter: ListDifferAdapter by lazy { adapterFactory.create() }

    private var clickedPosition = 0

    override val clicks: Consumer<Int> = Consumer { position ->
        clickedPosition = position
        uiActions.accept(HeroesListUiAction.HeroClicked(position))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        setExitSharedElementCallback(object: SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                val holder = rvHeroes.findViewHolderForAdapterPosition(clickedPosition) ?: return

                sharedElements[names.first()] = holder.itemView.tvHeroName
            }
        })
    }

    override fun onViewCreated() {
        super.onViewCreated()

        adapter
            .loadMoreObservable
            .map { HeroesListUiAction.LoadMoreHeroes }
            .emitUiAction()

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

    override fun provideSharedElement(): View {
        val holder = rvHeroes.findViewHolderForAdapterPosition(clickedPosition)!!

        return holder.itemView.tvHeroName
    }
}
