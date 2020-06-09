package com.arttttt.swapisampleribs.container.rib.heroes_list

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.container.rib.heroes_list.heroesListView.Event
import com.arttttt.swapisampleribs.container.rib.heroes_list.heroesListView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface heroesListView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, heroesListView>
}


class HeroesListViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : heroesListView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_heroeslist
    ) : heroesListView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> heroesListView = {
            HeroesListViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    override fun accept(vm: heroesListView.ViewModel) {
    }
}
