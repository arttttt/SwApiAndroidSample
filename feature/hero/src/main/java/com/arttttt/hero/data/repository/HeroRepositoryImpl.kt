package com.arttttt.hero.data.repository

import com.arttttt.hero.data.network.api.HeroApi
import com.arttttt.hero.domain.entity.Hero
import com.arttttt.hero.domain.repository.HeroRepository
import javax.inject.Inject

internal class HeroRepositoryImpl @Inject constructor(
    private val heroApi: HeroApi
) : HeroRepository {

    override suspend fun loadHero(id: Int): Hero {
        val response = heroApi.loadHero(id)

        return Hero(
            id = id,
            name = response.result.properties.name,
            birthYear = response.result.properties.birthYear,
            eyeColor = response.result.properties.eyeColor,
            gender = response.result.properties.gender,
            hairColor = response.result.properties.hairColor,
            height = response.result.properties.height,
            homeWorld = response.result.properties.homeWorld,
            mass = response.result.properties.mass,
            skinColor = response.result.properties.skinColor
        )
    }
}
