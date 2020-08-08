package com.arttttt.swapicompose.domain.entity

import com.arttttt.swapicompose.domain.entity.Hero

data class Heroes(
    val isAllHeroesLoaded: Boolean,
    val heroes: List<Hero>
)