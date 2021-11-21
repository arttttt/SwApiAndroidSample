package com.arttttt.heroeslist

import com.ewa.module_injector.BaseFeatureApi

interface HeroesListFeatureApi : BaseFeatureApi {
    val heroesListFeatureEntryPoint: HeroesListFeatureEntryPoint
}
