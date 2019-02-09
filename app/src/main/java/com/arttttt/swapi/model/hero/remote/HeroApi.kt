package com.arttttt.swapi.model.hero.remote

import com.arttttt.swapi.model.hero.Hero
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {
    @GET("people/")
    fun searchHero(@Query("search") nameHero: String): Single<HeroApiResponse<List<Hero>>>

    companion object Factory {
        fun create(): HeroApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://swapi.co/api/")
                .build()

            return retrofit.create(HeroApi::class.java)
        }
    }
}