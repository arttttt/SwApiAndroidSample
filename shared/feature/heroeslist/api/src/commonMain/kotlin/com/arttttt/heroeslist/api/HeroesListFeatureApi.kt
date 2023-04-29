package com.arttttt.heroeslist.api

import com.arttttt.moduleinjector.BaseFeatureApi

interface HeroesListFeatureApi : BaseFeatureApi {

    val heroesListComponentBuilder: HeroesListComponentBuilder
}
