package com.arttttt.swapisampleribs.rib.movies_list

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.arttttt.swapisampleribs.R
import com.arttttt.swapisampleribs.rib.movies_list.MoviesListView.Event
import com.arttttt.swapisampleribs.rib.movies_list.MoviesListView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface MoviesListView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, MoviesListView>
}


class MoviesListViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : MoviesListView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_movies
    ) : MoviesListView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> MoviesListView = {
            MoviesListViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    override fun accept(vm: MoviesListView.ViewModel) {
    }
}
