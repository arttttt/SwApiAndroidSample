package com.arttttt.swapisampleribs.domain.repository

import com.arttttt.swapisampleribs.domain.entity.Hero
import io.reactivex.Single

interface HeroesRepository {
    fun loadHeroes(): Single<List<Hero>>
}