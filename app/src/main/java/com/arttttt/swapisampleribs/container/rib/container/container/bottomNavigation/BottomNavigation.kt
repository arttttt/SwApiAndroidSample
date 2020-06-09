package com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation

import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.customisation.RibCustomisation
import com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation.BottomNavigation.Input
import com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation.BottomNavigation.Output

interface BottomNavigation : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: BottomNavigationView.Factory = BottomNavigationViewImpl.Factory()
    ) : RibCustomisation
}
