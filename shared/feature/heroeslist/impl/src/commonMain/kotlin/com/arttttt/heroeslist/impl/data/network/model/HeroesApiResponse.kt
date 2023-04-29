package com.arttttt.heroeslist.impl.data.network.model

import kotlinx.serialization.Serializable

@Serializable
internal class HeroesApiResponse(
    val next: String?,
    val results: List<HeroApiResponse>
)
