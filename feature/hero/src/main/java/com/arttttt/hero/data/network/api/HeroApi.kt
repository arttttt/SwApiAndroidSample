package com.arttttt.hero.data.network.api

import com.arttttt.hero.data.network.model.HeroApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HeroApi {

    @GET("people/{id}/")
    suspend fun loadHero(
        @Path("id") id: Int
    ): HeroApiResponse

}
