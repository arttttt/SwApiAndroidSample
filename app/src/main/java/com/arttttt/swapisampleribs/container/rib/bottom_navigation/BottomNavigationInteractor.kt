package com.arttttt.swapisampleribs.container.rib.bottom_navigation

import com.badoo.ribs.core.BackStackInteractor
import com.badoo.ribs.core.builder.BuildParams

class BottomNavigationInteractor(
    buildParams: BuildParams<Nothing?>
): BackStackInteractor<BottomNavigation, BottomNavigationView, BottomNavigationRouter.Configuration>(
    buildParams = buildParams,
    initialConfiguration = BottomNavigationRouter.Configuration.HeroesList
)