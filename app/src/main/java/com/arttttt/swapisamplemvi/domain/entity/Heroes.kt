package com.arttttt.swapisamplemvi.domain.entity

data class Heroes(
    val isAllHeroesLoaded: Boolean,
    val heroes: List<Hero>
)