package com.arttttt.swapicompose.data.network.model

import com.arttttt.swapicompose.data.network.model.HeroApiModel

class HeroApiResponse(
    val next: String?,
    val results: List<HeroApiModel>
)