package com.arttttt.swapi.data.models

class HeroApiResponse(
    val result: Properties
) {
    class Properties(
        val properties: HeroApiModel
    )
}
