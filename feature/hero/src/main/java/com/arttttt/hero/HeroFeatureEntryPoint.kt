package com.arttttt.hero

import androidx.core.os.bundleOf
import com.arttttt.dagger2.PerFeature
import com.arttttt.hero.ui.platform.HeroFragment
import com.arttttt.navigation.FeatureEntryPointProvider
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

@PerFeature
class HeroFeatureEntryPoint @Inject constructor() : FeatureEntryPointProvider<HeroFeatureEntryPoint.Arguments> {

    data class Arguments(
        val id: Int
    )

    override fun provideEntryPoint(arguments: Arguments): Screen {
        return FragmentScreen {
            HeroFragment().apply {
                this.arguments = bundleOf(
                    HeroFragment.HERO_ID to arguments.id
                )
            }
        }
    }
}
