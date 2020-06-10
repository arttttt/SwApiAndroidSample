package com.arttttt.swapisampleribs.container.rib.bottom_navigation

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.core.BackStackInteractor
import com.badoo.ribs.core.builder.BuildParams
import com.badoo.ribs.core.routing.configuration.feature.operation.replace
import io.reactivex.functions.Consumer

class BottomNavigationInteractor(
    buildParams: BuildParams<Nothing?>
): BackStackInteractor<BottomNavigation, BottomNavigationView, BottomNavigationRouter.Configuration>(
    buildParams = buildParams,
    initialConfiguration = BottomNavigationRouter.Configuration.HeroesList
) {
    override fun onViewCreated(view: BottomNavigationView, viewLifecycle: Lifecycle) {
        viewLifecycle.createDestroy {
            bind(view to Consumer { event ->
                when (event) {
                    is BottomNavigationView.Event.BottomTabSelected -> {
                        when (event.id) {
                            0 -> backStack.replace(BottomNavigationRouter.Configuration.HeroesList)
                            1 -> backStack.replace(BottomNavigationRouter.Configuration.MoviesList)
                        }
                    }
                }
            })
        }
    }
}