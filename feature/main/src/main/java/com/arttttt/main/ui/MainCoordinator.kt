package com.arttttt.main.ui

import com.arttttt.dagger2.PerFeature
import com.arttttt.heroeslist.HeroesListFeatureEntryPoint
import com.arttttt.navigation.Coordinator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

@PerFeature
internal class MainCoordinator @Inject constructor(
    private val router: Router,
    private val heroesListFeatureEntryPoint: HeroesListFeatureEntryPoint
) : Coordinator {

    fun start() {
        router.newRootScreen(heroesListFeatureEntryPoint.provideEntryPoint(Unit))
    }

    fun back() {
        router.exit()
    }

}
