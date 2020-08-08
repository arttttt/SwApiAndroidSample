package com.arttttt.swapicompose.data.network.api

import com.arttttt.swapicompose.data.network.model.HeroApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SwApi {
    @GET("people")
    fun searchHero(@Query("page") page: Int): Single<HeroApiResponse>
}