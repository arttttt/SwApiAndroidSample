package com.arttttt.heroeslist

import com.alphicc.brick.TreeRouter
import com.arttttt.moduleinjector.BaseFeatureDependencies
import com.arttttt.swapi.data.api.SwApi

interface HeroesListFeatureDependencies : BaseFeatureDependencies {

    val router: TreeRouter
    val swApi: SwApi
}
