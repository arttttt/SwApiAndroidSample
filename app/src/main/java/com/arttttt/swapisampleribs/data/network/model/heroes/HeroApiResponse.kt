package com.arttttt.swapisampleribs.data.network.model.heroes

import com.arttttt.swapisampleribs.data.network.model.SwApiResponse

class HeroApiResponse(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<HeroApiModel>
): SwApiResponse<HeroApiModel>()
