package com.arttttt.swapi.model.hero.repository

import com.arttttt.swapi.model.hero.Hero
import io.reactivex.Single

interface HeroRepository {
    fun getHero(name: String): Single<List<Hero>>
    fun getAllHeroes(): Single<List<Hero>>
}