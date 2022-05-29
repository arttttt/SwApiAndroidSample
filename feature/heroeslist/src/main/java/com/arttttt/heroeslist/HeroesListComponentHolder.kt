package com.arttttt.heroeslist

import com.arttttt.heroeslist.di.DaggerHeroesListFeatureComponent
import com.arttttt.heroeslist.di.HeroesListFeatureComponent
import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate

object HeroesListComponentHolder : ComponentHolder<HeroesListFeatureApi, HeroesListFeatureDependencies> {

    private val delegate = ComponentHolderDelegate<HeroesListFeatureApi, HeroesListFeatureDependencies, HeroesListFeatureComponent> { dependencies ->
        DaggerHeroesListFeatureComponent
            .factory()
            .create(dependencies)
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api: HeroesListFeatureApi
        get() = delegate.api
}
