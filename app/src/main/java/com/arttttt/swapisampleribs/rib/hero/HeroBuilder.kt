package com.arttttt.swapisampleribs.rib.hero

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class HeroBuilder(
    private val dependency: Hero.Dependency
) : SimpleBuilder<Hero>() {

    override fun build(buildParams: BuildParams<Nothing?>): Hero {
        val customisation = buildParams.getOrDefault(Hero.Customisation())

        return node(buildParams, customisation)
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Hero.Customisation
    ) =
        HeroNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = emptyList()
        )
}
