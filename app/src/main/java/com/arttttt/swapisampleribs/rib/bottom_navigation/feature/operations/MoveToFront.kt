package com.arttttt.swapisampleribs.rib.bottom_navigation.feature.operations

import android.os.Parcelable
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.BottomTabOperation
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.BottomTabs

internal class MoveToFront<C: Parcelable>(
    val configuration: C
): BottomTabOperation<C> {
    override fun invoke(tabs: BottomTabs<C>): BottomTabs<C> {
        return tabs
    }
}