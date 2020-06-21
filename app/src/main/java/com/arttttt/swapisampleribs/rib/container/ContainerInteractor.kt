package com.arttttt.swapisampleribs.rib.container

import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList
import com.badoo.ribs.core.BackStackInteractor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.configuration.feature.operation.push
import io.reactivex.functions.Consumer

class ContainerInteractor(
    buildParams: BuildParams<Nothing?>
): BackStackInteractor<Container, Nothing, ContainerRouter.Configuration>(
    buildParams = buildParams,
    initialConfiguration = ContainerRouter.Configuration.BottomNavigation
) {
    val heroesListOutput = Consumer<HeroesList.Output> { output ->
        when (output) {
            is HeroesList.Output.HeroClicked -> backStack.push(ContainerRouter.Configuration.Hero)
        }
    }
}