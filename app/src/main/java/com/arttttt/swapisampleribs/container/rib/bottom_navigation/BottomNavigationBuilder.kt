package com.arttttt.swapisampleribs.container.rib.bottom_navigation

import com.arttttt.swapisampleribs.container.rib.heroes_list.HeroesList
import com.arttttt.swapisampleribs.container.rib.heroes_list.HeroesListBuilder
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class BottomNavigationBuilder(
    private val dependency: BottomNavigation.Dependency
) : SimpleBuilder<BottomNavigation>() {

    override fun build(buildParams: BuildParams<Nothing?>): BottomNavigation {
        val customisation = buildParams.getOrDefault(BottomNavigation.Customisation())
        val interactor = BottomNavigationInteractor(buildParams)

        return node(
            buildParams =buildParams,
            customisation = customisation,
            interactor = interactor,
            router = BottomNavigationRouter(
                buildParams = buildParams,
                routingSource = interactor,
                heroesListBuilder = HeroesListBuilder(
                    object: HeroesList.Dependency {}
                )
            )
        )
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: BottomNavigation.Customisation,
        router: BottomNavigationRouter,
        interactor: BottomNavigationInteractor
    ) =
        BottomNavigationNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(router, interactor)
        )
}
