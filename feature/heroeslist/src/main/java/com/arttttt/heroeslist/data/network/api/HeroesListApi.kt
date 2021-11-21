package com.arttttt.heroeslist.data.network.api

import com.arttttt.heroeslist.data.network.model.HeroApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface HeroesListApi {
    @GET("people")
    suspend fun loadHeroesPage(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10,
    ): HeroApiResponse
}
