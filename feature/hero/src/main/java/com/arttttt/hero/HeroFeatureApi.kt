package com.arttttt.hero

import com.ewa.module_injector.BaseFeatureApi

interface HeroFeatureApi : BaseFeatureApi {
    val heroFeatureEntryPoint: HeroFeatureEntryPoint
}
