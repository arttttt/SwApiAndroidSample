package com.arttttt.swapisampleribs.rib.hero

import android.view.ViewGroup
import com.badoo.ribs.clienthelper.Connectable
import com.badoo.ribs.clienthelper.NodeConnector
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.arttttt.swapisampleribs.rib.hero.Hero.Input
import com.arttttt.swapisampleribs.rib.hero.Hero.Output

class HeroNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ((ViewGroup) -> HeroView?)?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : Node<HeroView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Hero, Connectable<Input, Output> by connector {

}
