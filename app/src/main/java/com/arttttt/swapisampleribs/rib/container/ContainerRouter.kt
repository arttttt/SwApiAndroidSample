package com.arttttt.swapisampleribs.rib.container

import android.os.Parcelable
import com.arttttt.swapisampleribs.rib.bottom_navigation.builder.BottomNavigationBuilder
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
    private val mainBuilder: BottomNavigationBuilder
): Router<ContainerRouter.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource
) {
    sealed class Configuration: Parcelable {
        @Parcelize object Main: Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): RoutingAction {
        return when (routing.configuration) {
            is Configuration.Main -> attach(mainBuilder::build)
        }
    }
}