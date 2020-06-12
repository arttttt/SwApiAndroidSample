package com.arttttt.swapisampleribs.rib.bottom_navigation.builder

import com.arttttt.swapisampleribs.extensions.closeOnNodeDetachPlugin
import com.arttttt.swapisampleribs.rib.KoinAwareSimpleBuilder
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigation
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigationInteractor
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigationNode
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigationRouter
import com.arttttt.swapisampleribs.rib.heroes_list.HeroesList
import com.arttttt.swapisampleribs.rib.heroes_list.builder.HeroesListBuilder
import com.arttttt.swapisampleribs.rib.movies_list.MoviesList
import com.arttttt.swapisampleribs.rib.movies_list.builder.MoviesListBuilder
import com.badoo.ribs.core.builder.BuildParams
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.qualifier

class BottomNavigationBuilder(
    private val dependency: BottomNavigation.Dependency
) : KoinAwareSimpleBuilder<BottomNavigation>() {

    override fun getScopeQualifier(): Qualifier {
        return qualifier<BottomNavigation>()
    }

    override fun build(buildParams: BuildParams<Nothing?>): BottomNavigation {
        return BottomNavigationNode(
            buildParams = buildParams,
            viewFactory = scope.get<BottomNavigation.Customisation> { parametersOf(buildParams) }.viewFactory(null),
            plugins = listOf(
                BottomNavigationRouter(
                    buildParams = buildParams,
                    routingSource = scope.get<BottomNavigationInteractor> { parametersOf(buildParams) },
                    heroesListBuilder = HeroesListBuilder(
                        dependency = object : HeroesList.Dependency {}
                    ),
                    moviesListBuilder = MoviesListBuilder(
                        dependency = object : MoviesList.Dependency {}
                    )
                ),
                scope.get<BottomNavigationInteractor> { parametersOf(buildParams) },
                scope.closeOnNodeDetachPlugin
            )
        )
    }
}
