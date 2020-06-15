package com.arttttt.swapisampleribs.data.repository

import com.arttttt.swapisampleribs.data.network.api.SwApi
import com.arttttt.swapisampleribs.domain.entity.Movie
import com.arttttt.swapisampleribs.domain.repository.MoviesRepository
import io.reactivex.Single

class MoviesRepositoryImpl(
    private val swApi: SwApi
): MoviesRepository {
    override fun loadMovies(): Single<List<Movie>> {
        return swApi
            .loadMovies(null)
            .map { response ->
                response.results.map { movie ->
                    Movie(
                        title = movie.title
                    )
                }
            }
    }
}