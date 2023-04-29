package com.arttttt.heroeslist.impl.data.network.api

import com.arttttt.heroeslist.impl.data.network.model.HeroesApiResponse

internal interface HeroesListApi {

    suspend fun loadHeroesPage(
        page: Int,
        limit: Int,
    ): HeroesApiResponse
}
