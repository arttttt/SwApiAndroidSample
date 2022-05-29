package com.arttttt.heroeslist

import com.alphicc.brick.Screen
import com.arttttt.moduleinjector.BaseFeatureApi

interface HeroesListFeatureApi : BaseFeatureApi {
    val heroesListScreen: Screen<*>
}
