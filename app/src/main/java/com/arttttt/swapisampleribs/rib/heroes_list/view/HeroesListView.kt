package com.arttttt.swapisampleribs.rib.heroes_list.view

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.recyclerview.IListItem
import com.arttttt.swapisampleribs.rib.heroes_list.view.adapter.delegates.HeroAdapterDelegate
import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView.Event
import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView.ViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface HeroesListView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val items: List<IListItem>
    )

    interface Factory : ViewFactory<Nothing?, HeroesListView>
}


class HeroesListViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : HeroesListView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_heroeslist
    ) : HeroesListView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> HeroesListView = {
            HeroesListViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    private val recyclerView = androidView.findViewById<RecyclerView>(R.id.recyclerView)

    private val adapter = ListDelegationAdapter(
        HeroAdapterDelegate()
    )

    init {
        recyclerView.layoutManager = LinearLayoutManager(androidView.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun accept(vm: ViewModel) {
        adapter.items = vm.items
        adapter.notifyDataSetChanged()
    }
}
