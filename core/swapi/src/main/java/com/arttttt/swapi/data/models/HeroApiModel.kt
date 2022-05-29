package com.arttttt.swapi.data.models

import com.google.gson.annotations.SerializedName

class HeroApiModel(
    @SerializedName("uid")
    val id: Int,
    val name: String
)
