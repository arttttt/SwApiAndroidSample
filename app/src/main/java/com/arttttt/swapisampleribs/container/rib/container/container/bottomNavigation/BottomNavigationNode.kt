package com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation

import android.view.ViewGroup
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.clienthelper.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation.BottomNavigation.Input
import com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation.BottomNavigation.Output

class BottomNavigationNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> BottomNavigationView?)?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : Node<BottomNavigationView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), BottomNavigation, Connectable<Input, Output> by connector {

}
