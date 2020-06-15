package com.arttttt.swapisampleribs.data.repository

import com.arttttt.swapisampleribs.data.network.api.SwApi
import com.arttttt.swapisampleribs.domain.entity.Hero
import com.arttttt.swapisampleribs.domain.repository.HeroesRepository
import io.reactivex.Single

class HeroesRepositoryImpl(
    private val swApi: SwApi
): HeroesRepository {
    override fun loadHeroes(): Single<List<Hero>> {
        return swApi
            .loadHeroes(null)
            .map { response ->
                response
                    .results
                    .map { hero ->
                        Hero(
                            name = hero.name,
                            birthYear = hero.birthYear
                        )
                    }
            }
    }
}