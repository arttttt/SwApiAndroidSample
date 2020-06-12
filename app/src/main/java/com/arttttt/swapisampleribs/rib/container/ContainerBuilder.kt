package com.arttttt.swapisampleribs.rib.container

import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigation
import com.arttttt.swapisampleribs.rib.bottom_navigation.builder.BottomNavigationBuilder
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.builder.SimpleBuilder

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
                mainBuilder = BottomNavigationBuilder(
                    object : BottomNavigation.Dependency {})
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
