package com.arttttt.heroeslist.ui.list.di

import com.alphicc.brick.TreeRouter
import com.arttttt.swapi.data.api.SwApi

internal interface HeroesListDependencies {

    val swApi: SwApi
    val router: TreeRouter
}
