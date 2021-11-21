package com.arttttt.navigation

import com.ewa.module_injector.BaseFeatureApi
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface NavigationFeatureApi : BaseFeatureApi {
    val router: Router
    val navigatorHolder: NavigatorHolder
}
