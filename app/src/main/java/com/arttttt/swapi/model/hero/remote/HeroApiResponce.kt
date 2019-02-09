package com.arttttt.swapi.model.hero.remote

import com.google.gson.annotations.SerializedName

class HeroApiResponse<T>(@SerializedName("results") val results: T)