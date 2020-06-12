package com.arttttt.swapisampleribs.data.network.model

import com.google.gson.annotations.SerializedName

class HeroApiModel(
    @SerializedName("birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    val gender: String,
    @SerializedName("hair_color")
    val hairColor: String,
    val height: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    val mass: String,
    val name: String,
    @SerializedName("skin_color")
    val skinColor: String,
    val url: String
)