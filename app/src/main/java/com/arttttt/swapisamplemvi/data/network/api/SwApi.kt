package com.arttttt.swapisamplemvi.data.network.api

import com.arttttt.swapisamplemvi.data.network.model.HeroApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SwApi {
    @GET("people")
    fun searchHero(): Single<HeroApiResponse>
}