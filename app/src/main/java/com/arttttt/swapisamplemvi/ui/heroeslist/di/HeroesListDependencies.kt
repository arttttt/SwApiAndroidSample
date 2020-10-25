package com.arttttt.swapisamplemvi.ui.heroeslist.di

import com.arttttt.swapisamplemvi.domain.feature.herofeature.HeroFeature
import com.arttttt.swapisamplemvi.domain.repository.SwRepository
import com.arttttt.swapisamplemvi.ui.RootCoordinator

interface HeroesListDependencies {
    fun provideRootCoordinator(): RootCoordinator
    fun provideSwRepository(): SwRepository
    fun provideHeroFeature(): HeroFeature
}