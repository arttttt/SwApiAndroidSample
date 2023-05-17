package com.arttttt.heroeslist.impl.domain.repository

import com.arttttt.heroeslist.api.entity.Hero

internal interface HeroesListRepository {
    suspend fun loadHeroes(page: Int): List<Hero>
}
