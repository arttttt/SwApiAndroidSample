package com.arttttt.swapisampleribs.container.rib.bottom_navigation

import android.os.Parcelable
import com.arttttt.swapisampleribs.container.rib.heroes_list.HeroesListBuilder
import com.arttttt.swapisampleribs.container.rib.movies_list.MoviesListBuilder
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.action.AttachRibRoutingAction.Companion.attach
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.history.Routing
import kotlinx.android.parcel.Parcelize

class BottomNavigationRouter(
    buildParams: BuildParams<Nothing?>,
    routingSource: RoutingSource<Configuration>,
    private val heroesListBuilder: HeroesListBuilder,
    private val moviesListBuilder: MoviesListBuilder
): Router<BottomNavigationRouter.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource
) {
    sealed class Configuration: Parcelable {
        @Parcelize object HeroesList: Configuration()
        @Parcelize object MoviesList: Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): RoutingAction {
        return when (routing.configuration) {
            is Configuration.HeroesList -> attach(heroesListBuilder::build)
            is Configuration.MoviesList -> attach(moviesListBuilder::build)
        }
    }
}