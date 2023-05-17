package com.arttttt.hero.api

import com.arttttt.moduleinjector.BaseFeatureDependencies

interface HeroFeatureDependencies : BaseFeatureDependencies {

    val heroViewFactory: () -> HeroView
}
