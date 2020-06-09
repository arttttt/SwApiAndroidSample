package com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class BottomNavigationBuilder(
    private val dependency: BottomNavigation.Dependency
) : SimpleBuilder<BottomNavigation>() {

    override fun build(buildParams: BuildParams<Nothing?>): BottomNavigation {
        val customisation = buildParams.getOrDefault(BottomNavigation.Customisation())

        return node(buildParams, customisation)
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: BottomNavigation.Customisation
    ) =
        BottomNavigationNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = emptyList()
        )
}
