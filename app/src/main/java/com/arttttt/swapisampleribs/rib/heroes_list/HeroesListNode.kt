package com.arttttt.swapisampleribs.rib.heroes_list

import android.view.ViewGroup
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList.Input
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList.Output
import com.arttttt.swapisampleribs.rib.heroes_list.view.HeroesListView
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.clienthelper.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.Plugin

class HeroesListNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> HeroesListView?)?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : Node<HeroesListView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), HeroesList, Connectable<Input, Output> by connector {}
