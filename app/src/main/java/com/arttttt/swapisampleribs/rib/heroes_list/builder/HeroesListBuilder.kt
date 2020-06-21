package com.arttttt.swapisampleribs.rib.heroes_list.builder

import com.arttttt.swapisampleribs.rib.KoinAwareSimpleBuilder
import com.arttttt.swapisampleribs.extensions.closeOnNodeDetachPlugin
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesListInteractor
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesListNode
import com.badoo.ribs.core.builder.BuildParams
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.qualifier

class HeroesListBuilder(
    dependency: HeroesList.Dependency
) : KoinAwareSimpleBuilder<HeroesList>() {

    init {
        scope.declare(dependency)
    }

    override fun getScopeQualifier(): Qualifier {
        return qualifier<HeroesList>()
    }

    override fun build(buildParams: BuildParams<Nothing?>): HeroesList {
        return HeroesListNode(
            buildParams = buildParams,
            viewFactory = scope.get<HeroesList.Customisation> {
                parametersOf(
                    buildParams
                )
            }.viewFactory(null),
            plugins = listOf(
                scope.get<HeroesListInteractor> {
                    parametersOf(
                        buildParams
                    )
                },
                scope.closeOnNodeDetachPlugin
            )
        )
    }
}
