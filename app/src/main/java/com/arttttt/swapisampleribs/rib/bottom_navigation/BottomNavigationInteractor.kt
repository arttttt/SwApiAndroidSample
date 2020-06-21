package com.arttttt.swapisampleribs.rib.bottom_navigation

import androidx.lifecycle.Lifecycle
import com.arttttt.swapisampleribs.rib.bottom_navigation.feature.BottomNavigationFeature
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.RoutingSource
import io.reactivex.functions.Consumer

class BottomNavigationInteractor private constructor(
    buildParams: BuildParams<*>,
    val bottomTabs: BottomNavigationFeature<BottomNavigationRouter.Configuration>
): Interactor<BottomNavigation, BottomNavigationView>(
    buildParams = buildParams,
    disposables = null
), RoutingSource<BottomNavigationRouter.Configuration> by bottomTabs {

    constructor(buildParams: BuildParams<*>): this(
        buildParams = buildParams,
        bottomTabs = BottomNavigationFeature(
            buildParams = buildParams,
            tabs = setOf(
                BottomNavigationRouter.Configuration.HeroesList,
                BottomNavigationRouter.Configuration.MoviesList
            )
        )
    )

    override fun onViewCreated(view: BottomNavigationView, viewLifecycle: Lifecycle) {
        viewLifecycle.createDestroy {
            bind(view to Consumer { event ->
                when (event) {
                    is BottomNavigationView.Event.BottomTabSelected -> {
                        when (event.id) {
                            0 -> bottomTabs.moveToFront(BottomNavigationRouter.Configuration.HeroesList)
                            1 -> bottomTabs.moveToFront(BottomNavigationRouter.Configuration.MoviesList)
                        }
                    }
                }
            })
        }
    }
}