package com.arttttt.swapisampleribs.data.network.api

import com.arttttt.swapisampleribs.data.network.model.heroes.HeroApiResponse
import com.arttttt.swapisampleribs.data.network.model.movies.MovieApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SwApi {
    @GET("people")
    fun loadHeroes(@Query("page") page: Int?): Single<HeroApiResponse>

    @GET("films")
    fun loadMovies(@Query("page") page: Int?): Single<MovieApiResponse>
}