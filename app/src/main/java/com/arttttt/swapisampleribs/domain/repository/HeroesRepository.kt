package com.arttttt.swapisampleribs.domain.repository

import com.arttttt.swapisampleribs.domain.entity.Hero
import com.arttttt.swapisampleribs.domain.entity.Movie
import io.reactivex.Single

interface HeroesRepository {
    fun loadHeroes(): Single<List<Hero>>
}