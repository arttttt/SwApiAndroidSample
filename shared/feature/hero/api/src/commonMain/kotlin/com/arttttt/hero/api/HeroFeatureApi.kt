package com.arttttt.hero.api

import com.arttttt.moduleinjector.BaseFeatureApi

interface HeroFeatureApi : BaseFeatureApi {

    val heroComponentBuilder: HeroComponentBuilder
}
