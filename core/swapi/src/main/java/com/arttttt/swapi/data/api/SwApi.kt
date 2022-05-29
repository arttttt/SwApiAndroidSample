package com.arttttt.swapi.data.api

import com.arttttt.swapi.data.models.HeroApiResponse
import com.arttttt.swapi.data.models.HeroListApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwApi {

    @GET("people")
    suspend fun loadHeroesPage(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10,
    ): HeroListApiResponse

    @GET("people/{id}/")
    suspend fun loadHero(
        @Path("id") id: Int
    ): HeroApiResponse
}
