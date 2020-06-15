package com.arttttt.swapisampleribs.rib.movies_list.builder

import com.arttttt.swapisampleribs.extensions.closeOnNodeDetachPlugin
import com.arttttt.swapisampleribs.rib.KoinAwareSimpleBuilder
import com.arttttt.swapisampleribs.rib.movies_list.MoviesList
import com.arttttt.swapisampleribs.rib.movies_list.MoviesListInteractor
import com.arttttt.swapisampleribs.rib.movies_list.MoviesListNode
import com.badoo.ribs.core.builder.BuildParams
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.qualifier

class MoviesListBuilder(
    private val dependency: MoviesList.Dependency
) : KoinAwareSimpleBuilder<MoviesList>() {

    override fun getScopeQualifier(): Qualifier {
        return qualifier<MoviesList>()
    }

    override fun build(buildParams: BuildParams<Nothing?>): MoviesList {
        return MoviesListNode(
            buildParams = buildParams,
            viewFactory = scope.get<MoviesList.Customisation> {
                parametersOf(
                    buildParams
                )
            }.viewFactory(null),
            plugins = listOf(
                scope.get<MoviesListInteractor> { parametersOf(buildParams) },
                scope.closeOnNodeDetachPlugin
            )
        )
    }
}
