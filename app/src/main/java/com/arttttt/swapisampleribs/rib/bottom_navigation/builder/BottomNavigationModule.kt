package com.arttttt.swapisampleribs.rib.bottom_navigation.builder

import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigation
import com.arttttt.swapisampleribs.rib.bottom_navigation.BottomNavigationInteractor
import com.badoo.ribs.core.builder.BuildParams
import org.koin.dsl.module

val bottomNavigationModule = module {
    scope<BottomNavigation> {
        scoped { (buildParams: BuildParams<*>) ->
            buildParams.getOrDefault(BottomNavigation.Customisation())
        }

        scoped { (buildParams: BuildParams<*>) ->
            BottomNavigationInteractor(
                buildParams
            )
        }
    }
}