package com.arttttt.swapisampleribs.data.network.model.movies

import com.arttttt.swapisampleribs.data.network.model.SwApiResponse

class MovieApiResponse(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<MovieApiModel>
): SwApiResponse<MovieApiModel>()