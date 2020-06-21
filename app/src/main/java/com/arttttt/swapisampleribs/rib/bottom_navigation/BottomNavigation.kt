package com.arttttt.swapisampleribs.rib.bottom_navigation

import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigation.Input
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigation.Output
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList

interface BottomNavigation : Rib, Connectable<Input, Output> {

    interface Dependency: HeroesList.Dependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: BottomNavigationView.Factory = BottomNavigationViewImpl.Factory()
    ) : RibCustomisation
}
