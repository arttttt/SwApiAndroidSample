package com.arttttt.swapisampleribs.rib.movies_list

import androidx.lifecycle.Lifecycle
import com.arttttt.swapisampleribs.rib.movies_list.feature.MoviesListFeature
import com.arttttt.swapisampleribs.rib.movies_list.view.MoviesListView
import com.arttttt.swapisampleribs.rib.movies_list.view.adapter.models.MovieAdapterItem
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams

class MoviesListInteractor(
    buildParams: BuildParams<*>,
    private val moviesFeature: MoviesListFeature
): Interactor<MoviesList, MoviesListView>(
    buildParams = buildParams,
    disposables = moviesFeature
) {
    override fun onViewCreated(view: MoviesListView, viewLifecycle: Lifecycle) {
        viewLifecycle.createDestroy {
            bind(moviesFeature to view using { state ->
                MoviesListView.ViewModel(
                    items = state.movies.map { movie ->
                        MovieAdapterItem(
                            title = movie.title
                        )
                    }
                )
            })
        }
    }
}