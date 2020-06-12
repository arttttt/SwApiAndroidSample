package com.arttttt.swapisampleribs.rib.movies_list

import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import com.arttttt.swapisampleribs.rib.movies_list.MoviesList.Input
import com.arttttt.swapisampleribs.rib.movies_list.MoviesList.Output

interface MoviesList : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: MoviesListView.Factory = MoviesListViewImpl.Factory()
    ) : RibCustomisation
}
