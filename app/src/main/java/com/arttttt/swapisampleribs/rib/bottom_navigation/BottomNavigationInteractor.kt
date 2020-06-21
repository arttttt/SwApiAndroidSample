package com.arttttt.swapisampleribs.rib.bottom_navigation

import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import com.arttttt.swapisampleribs.rib.TestFeature
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.core.BackStackInteractor
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.RoutingSource
import com.badoo.ribs.core.routing.configuration.feature.BackStackFeature
import com.badoo.ribs.core.routing.configuration.feature.operation.singleTop
import com.badoo.ribs.core.view.RibView
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class BottomTabInteractor<R : Rib, V : RibView, C : Parcelable>(
    buildParams: BuildParams<*>,
    disposables: Disposable? = null,
    val bottomTabs: TestFeature<C>
) : Interactor<R, V>(
    buildParams = buildParams,
    disposables = disposables
), RoutingSource<C> by bottomTabs {

    constructor(
        buildParams: BuildParams<*>,
        tabs: Set<C>,
        disposables: Disposable? = null
    ) : this(
        buildParams = buildParams,
        disposables = disposables,
        bottomTabs = TestFeature(
            tabs = tabs
        )
    )
}

class BottomNavigationInteractor(
    buildParams: BuildParams<*>
): BottomTabInteractor<BottomNavigation, BottomNavigationView, BottomNavigationRouter.Configuration>(
    buildParams = buildParams,
    tabs = setOf(
        BottomNavigationRouter.Configuration.HeroesList,
        BottomNavigationRouter.Configuration.MoviesList
    )
) {
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
