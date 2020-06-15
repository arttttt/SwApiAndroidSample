package com.arttttt.swapisampleribs.rib.movies_list.builder

import com.arttttt.swapisampleribs.rib.movies_list.MoviesList
import com.arttttt.swapisampleribs.rib.movies_list.MoviesListInteractor
import com.arttttt.swapisampleribs.rib.movies_list.feature.MoviesListFeature
import com.badoo.ribs.core.builder.BuildParams
import org.koin.dsl.module

val moviesListModule = module {
    scope<MoviesList> {
        scoped { (buildParams: BuildParams<*>) ->
            buildParams.getOrDefault(MoviesList.Customisation())
        }

        scoped {
            MoviesListFeature(
                moviesRepository = get()
            )
        }

        scoped { (buildParams: BuildParams<*>) ->
            MoviesListInteractor(
                buildParams = buildParams,
                moviesFeature = get()
            )
        }
    }
}