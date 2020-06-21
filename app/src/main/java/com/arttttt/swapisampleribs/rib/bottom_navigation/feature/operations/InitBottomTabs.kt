package com.arttttt.swapisampleribs.rib.bottom_navigation.feature.operations

import android.os.Parcelable
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.BottomTabOperation
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.BottomTabs
import com.badoo.ribs.core.routing.history.Routing
import com.badoo.ribs.core.routing.history.RoutingHistoryElement

internal class InitBottomTabs<C : Parcelable>(
    private val configurations: Set<C>
): BottomTabOperation<C> {

    override fun invoke(tabs: BottomTabs<C>): BottomTabs<C> {
        return configurations
            .map { configuration -> RoutingHistoryElement(Routing(configuration)) }
            .toSet()
    }
}