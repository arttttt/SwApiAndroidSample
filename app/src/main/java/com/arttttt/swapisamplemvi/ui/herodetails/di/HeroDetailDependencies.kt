package com.arttttt.swapisamplemvi.ui.herodetails.di

import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.ui.RootCoordinator

interface HeroDetailDependencies {
    fun provideRootCoordinator(): RootCoordinator
    fun provideHeroFeature(): HeroFeature
}