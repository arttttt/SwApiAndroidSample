package com.arttttt.swapisampleribs.container.rib.movies_list

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class MoviesListBuilder(
    private val dependency: MoviesList.Dependency
) : SimpleBuilder<MoviesList>() {

    override fun build(buildParams: BuildParams<Nothing?>): MoviesList {
        val customisation = buildParams.getOrDefault(MoviesList.Customisation())

        return node(buildParams, customisation)
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: MoviesList.Customisation
    ) =
        MoviesListNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = emptyList()
        )
}
