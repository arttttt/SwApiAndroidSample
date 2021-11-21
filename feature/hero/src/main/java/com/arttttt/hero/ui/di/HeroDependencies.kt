package com.arttttt.hero.ui.di

import com.arttttt.hero.domain.repository.HeroRepository

internal interface HeroDependencies {
    val heroRepository: HeroRepository
}
