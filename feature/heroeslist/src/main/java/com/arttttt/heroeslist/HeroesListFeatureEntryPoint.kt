package com.arttttt.heroeslist

import com.arttttt.dagger2.PerFeature
import com.arttttt.heroeslist.ui.platform.HeroesListFragment
import com.arttttt.navigation.FeatureEntryPointProvider
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

@PerFeature
class HeroesListFeatureEntryPoint @Inject constructor() : FeatureEntryPointProvider<Unit> {
    override fun provideEntryPoint(arguments: Unit): Screen {
        return FragmentScreen {
            HeroesListFragment()
        }
    }
}
