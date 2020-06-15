package com.arttttt.swapisampleribs.domain.repository

import com.arttttt.swapisampleribs.domain.entity.Movie
import io.reactivex.Single

interface MoviesRepository {
    fun loadMovies(): Single<List<Movie>>
}