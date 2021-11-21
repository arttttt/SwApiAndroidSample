package com.arttttt.heroeslist

import android.content.Context
import com.arttttt.hero.HeroFeatureEntryPoint
import com.ewa.module_injector.BaseFeatureDependencies
import com.github.terrakok.cicerone.Router

interface HeroesListFeatureDependencies : BaseFeatureDependencies {
    val heroFeatureEntryPoint: HeroFeatureEntryPoint
    val router: Router
    val context: Context
}
