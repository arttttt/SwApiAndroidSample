package com.arttttt.main

import com.arttttt.heroeslist.HeroesListFeatureEntryPoint
import com.ewa.module_injector.BaseFeatureDependencies
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface MainFeatureDependencies : BaseFeatureDependencies {
    val heroesListFeatureEntryPoint: HeroesListFeatureEntryPoint
    val router: Router
    val navigatorHolder: NavigatorHolder
}
