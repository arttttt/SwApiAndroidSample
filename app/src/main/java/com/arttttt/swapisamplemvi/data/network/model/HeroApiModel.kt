package com.arttttt.swapisamplemvi.data.network.model

import com.squareup.moshi.Json

class HeroApiModel(
    @Json(name = "birth_year")
    val birthYear: String,
    val created: String,
    val edited: String,
    @Json(name = "eye_color")
    val eyeColor: String,
    val gender: String,
    @Json(name = "hair_color")
    val hairColor: String,
    val height: String,
    @Json(name = "homeworld")
    val homeWorld: String,
    val mass: String,
    val name: String,
    @Json(name = "skin_color")
    val skinColor: String,
    val url: String
)