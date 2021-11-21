package com.arttttt.heroeslist.data.network.model

import com.google.gson.annotations.SerializedName

internal class HeroApiModel(
    @SerializedName("uid")
    val id: Int,
    val name: String
)
