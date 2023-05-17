package com.arttttt.heroeslist.impl

import com.arttttt.heroeslist.api.HeroesListComponentBuilder
import com.arttttt.heroeslist.api.HeroesListFeatureApi
import com.arttttt.heroeslist.api.HeroesListFeatureDependencies
import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate

object HeroesListComponentHolder : ComponentHolder<HeroesListFeatureApi, HeroesListFeatureDependencies>() {

    private val delegate = ComponentHolderDelegate { dependencies: HeroesListFeatureDependencies ->
        object : HeroesListFeatureApi {
            override val heroesListComponentBuilder: HeroesListComponentBuilder =
                HeroesListComponentBuilderImpl(
                    listViewFactory = dependencies.listViewFactory,
                    toolbarViewFactory = dependencies.toolbarViewFactory,
                )
        }
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api by delegate::api
}
