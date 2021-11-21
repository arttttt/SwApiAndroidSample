package com.arttttt.heroeslist.domain.repository

import com.arttttt.heroeslist.domain.entity.Hero

internal interface HeroesListRepository {
    suspend fun loadHeroes(page: Int): List<Hero>
}
