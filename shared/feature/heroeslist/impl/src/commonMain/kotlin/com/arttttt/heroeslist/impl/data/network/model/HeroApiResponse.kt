package com.arttttt.heroeslist.impl.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class HeroApiResponse(
    @SerialName("uid")
    val id: Int,
    val name: String
)
