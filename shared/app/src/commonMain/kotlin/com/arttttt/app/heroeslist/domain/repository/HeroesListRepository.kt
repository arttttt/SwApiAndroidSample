package com.arttttt.app.heroeslist.domain.repository

import com.arttttt.app.heroeslist.domain.entity.Hero

internal interface HeroesListRepository {
    suspend fun loadHeroes(page: Int): List<Hero>
}
