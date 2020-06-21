package com.arttttt.swapisampleribs.rib.bottom_navigation.feature

import android.os.Parcelable
import com.badoo.ribs.core.routing.history.RoutingHistoryElement

interface BottomTabOperation<C : Parcelable> : (BottomTabs<C>) -> BottomTabs<C>

typealias BottomTabs<C> = Set<RoutingHistoryElement<C>>
