package com.arttttt.heroeslist.data.repository

import com.arttttt.heroeslist.domain.entity.Hero
import com.arttttt.heroeslist.domain.repository.HeroesListRepository
import com.arttttt.swapi.data.api.SwApi
import javax.inject.Inject

internal class HeroesListRepositoryImpl @Inject constructor(
    private val heroesListApi: SwApi
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
