package com.arttttt.heroeslist.data.repository

import com.arttttt.heroeslist.data.network.api.HeroesListApi
import com.arttttt.heroeslist.domain.entity.Hero
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import javax.inject.Inject

internal class HeroesListRepositoryImpl @Inject constructor(
    private val heroesListApi: HeroesListApi
) : HeroesListRepository {
    override suspend fun loadHeroes(page: Int): List<Hero> {
        val response = heroesListApi.loadHeroesPage(page)

        return response.results.map { hero ->
            Hero(
                id = hero.id,
                name = hero.name
            )
        }
    }
}
