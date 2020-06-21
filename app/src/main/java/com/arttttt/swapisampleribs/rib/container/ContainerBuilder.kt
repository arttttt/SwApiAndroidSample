package com.arttttt.swapisampleribs.rib.container

import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigation
import com.arttttt.swapisampleribs.rib.bottom_navigation.builder.BottomNavigationBuilder
import com.arttttt.swapisampleribs.rib.hero.Hero
import com.arttttt.swapisampleribs.rib.hero.HeroBuilder
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder
import io.reactivex.functions.Consumer

class ContainerBuilder(
    private val dependency: Container.Dependency
) : SimpleBuilder<Container>() {

    override fun build(
        buildParams: BuildParams<Nothing?>
    ): Container {
        val interactor = interactor(
            buildParams = buildParams
        )

        return node(
            buildParams = buildParams,
            router = ContainerRouter(
                buildParams = buildParams,
                routingSource = interactor,
                bottomNavigationBuilder = BottomNavigationBuilder(
                    object: BottomNavigation.Dependency {
                        override fun output(): Consumer<HeroesList.Output> {
                            return interactor.heroesListOutput
                        }
                    }),
                heroBuilder = HeroBuilder(
                    object: Hero.Dependency {}
                )
            ),
            interactor = interactor
        )
    }

    private fun interactor(
        buildParams: BuildParams<Nothing?>
    ): ContainerInteractor {
        return ContainerInteractor(
            buildParams = buildParams
        )
    }

    private fun node(
        buildParams: BuildParams<Nothing?>,
        router: ContainerRouter,
        interactor: ContainerInteractor
    ): ContainerNode {
        return ContainerNode(
            buildParams,
            plugins = listOf(router, interactor)
        )
    }
}
