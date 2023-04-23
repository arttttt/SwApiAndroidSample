package com.arttttt.app.heroeslist.data.network.api

import com.arttttt.app.heroeslist.data.network.model.HeroesApiResponse

internal interface HeroesListApi {

    suspend fun loadHeroesPage(
        page: Int,
        limit: Int,
    ): HeroesApiResponse
}
