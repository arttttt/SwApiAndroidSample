package com.arttttt.swapisampleribs.rib.bottom_navigation.feature

import android.os.Parcelable
import com.badoo.ribs.core.routing.history.RoutingHistory
import com.badoo.ribs.core.routing.history.RoutingHistoryElement
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BottomNavigationFeatureState<C : Parcelable>(
    val bottomTabs: BottomTabs<C> = emptySet()
) : Parcelable, RoutingHistory<C> {

    override fun iterator(): Iterator<RoutingHistoryElement<C>> {
        return bottomTabs.iterator()
    }
}