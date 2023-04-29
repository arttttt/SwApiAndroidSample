package com.arttttt.heroeslist.impl

import com.arttttt.heroeslist.api.HeroesListComponentBuilder
import com.arttttt.heroeslist.api.HeroesListFeatureApi
import com.arttttt.heroeslist.api.HeroesListFeatureDependencies
import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate

object HeroesListComponentHolder : ComponentHolder<HeroesListFeatureApi, HeroesListFeatureDependencies> {

    private val delegate = ComponentHolderDelegate { _: HeroesListFeatureDependencies ->
        object : HeroesListFeatureApi {
            override val heroesListComponentBuilder: HeroesListComponentBuilder = HeroesListComponentBuilderImpl()
        }
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api: HeroesListFeatureApi
        get() = delegate.api
}
