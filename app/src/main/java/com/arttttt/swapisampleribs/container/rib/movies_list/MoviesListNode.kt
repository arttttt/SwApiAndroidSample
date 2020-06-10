package com.arttttt.swapisampleribs.container.rib.movies_list

import android.view.ViewGroup
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.clienthelper.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.arttttt.swapisampleribs.container.rib.movies_list.MoviesList.Input
import com.arttttt.swapisampleribs.container.rib.movies_list.MoviesList.Output

class MoviesListNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> MoviesListView?)?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : Node<MoviesListView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), MoviesList, Connectable<Input, Output> by connector {

}
