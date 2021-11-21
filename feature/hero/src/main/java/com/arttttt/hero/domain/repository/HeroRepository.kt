package com.arttttt.hero.domain.repository

import com.arttttt.hero.domain.entity.Hero

internal interface HeroRepository {
    suspend fun loadHero(id: Int): Hero
}
