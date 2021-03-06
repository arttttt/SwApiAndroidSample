package com.arttttt.swapisampleribs.rib.container

import android.os.Parcelable
import com.arttttt.swapisampleribs.rib.bottom_navigation.builder.BottomNavigationBuilder
import com.arttttt.swapisampleribs.rib.hero.HeroBuilder
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.action.AttachRibRoutingAction.Companion.attach
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.history.Routing
import kotlinx.android.parcel.Parcelize

class ContainerRouter(
    buildParams: BuildParams<Nothing?>,
    routingSource: RoutingSource<Configuration>,
    private val bottomNavigationBuilder: BottomNavigationBuilder,
    private val heroBuilder: HeroBuilder
): Router<ContainerRouter.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource
) {
    sealed class Configuration: Parcelable {
        @Parcelize
        object BottomNavigation: Configuration()

        @Parcelize
        object Hero: Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): RoutingAction {
        return when (routing.configuration) {
            is Configuration.BottomNavigation -> attach(bottomNavigationBuilder::build)
            is Configuration.Hero -> attach(heroBuilder::build)
        }
    }
}