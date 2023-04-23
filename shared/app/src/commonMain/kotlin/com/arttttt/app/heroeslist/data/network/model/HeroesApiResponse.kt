package com.arttttt.app.heroeslist.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal class HeroesApiResponse(
    val next: String?,
    val results: List<HeroApiResponse>
)
