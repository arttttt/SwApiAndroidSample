package com.arttttt.swapisampleribs.container.rib.heroes_list

import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import com.arttttt.swapisampleribs.container.rib.heroes_list.feature.HeroesListFeature

class HeroesListBuilder(
    private val dependency: HeroesList.Dependency
) : SimpleBuilder<HeroesList>() {

    override fun build(buildParams: BuildParams<Nothing?>): HeroesList {
        val customisation = buildParams.getOrDefault(HeroesList.Customisation())
        val feature = feature()
        val interactor = interactor(buildParams, feature)

        return node(buildParams, customisation, interactor)
    }

    private fun feature() =
        HeroesListFeature()

    private fun interactor(
        buildParams: BuildParams<*>,
        feature: HeroesListFeature
    ) =
        HeroesListInteractor(
            buildParams = buildParams,
            feature = feature
        )

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: HeroesList.Customisation,
        interactor: HeroesListInteractor
    ) =
        HeroesListNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor)
        )
}
