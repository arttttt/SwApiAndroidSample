package com.arttttt.swapisampleribs.container.rib.container.container.container

import com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation.BottomNavigation
import com.arttttt.swapisampleribs.container.rib.container.container.bottomNavigation.BottomNavigationBuilder
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

class ContainerBuilder(
    private val dependency: Container.Dependency
) : SimpleBuilder<Container>() {

    override fun build(
        buildParams: BuildParams<Nothing?>
    ): Container {
        return node(
            buildParams = buildParams,
            router = ContainerRouter(
                buildParams = buildParams,
                routingSource = interactor(
                    buildParams = buildParams
                ),
                mainBuilder = BottomNavigationBuilder(object: BottomNavigation.Dependency {})
            )
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
        router: ContainerRouter
    ): ContainerNode {
        return ContainerNode(
            buildParams,
            plugins = listOf(router)
        )
    }
}
