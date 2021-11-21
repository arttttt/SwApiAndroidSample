package com.arttttt.hero.data.network.model

internal class HeroApiResponse(
    val result: Properties
) {
    internal class Properties(
        val properties: HeroApiModel
    )
}
