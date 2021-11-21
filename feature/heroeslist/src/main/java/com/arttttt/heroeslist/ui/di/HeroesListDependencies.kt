package com.arttttt.heroeslist.ui.di

import android.content.Context
import com.arttttt.hero.HeroFeatureEntryPoint
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import com.github.terrakok.cicerone.Router

internal interface HeroesListDependencies {
    val heroesListRepository: HeroesListRepository
    val router: Router
    val heroFeatureEntryPoint: HeroFeatureEntryPoint
    val context: Context
}
