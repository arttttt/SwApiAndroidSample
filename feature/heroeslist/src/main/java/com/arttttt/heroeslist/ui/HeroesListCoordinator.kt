package com.arttttt.heroeslist.ui

import com.arttttt.dagger2.PerScreen
import com.arttttt.hero.HeroFeatureEntryPoint
import com.arttttt.navigation.Coordinator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

@PerScreen
internal class HeroesListCoordinator @Inject constructor(
    private val router: Router,
    private val heroFeatureEntryPoint: HeroFeatureEntryPoint
) : Coordinator {

    fun openHero(id: Int) {
        router.navigateTo(
            heroFeatureEntryPoint.provideEntryPoint(
                arguments = HeroFeatureEntryPoint.Arguments(
                    id = id
                )
            )
        )
    }

}
