package com.arttttt.heroeslist

import com.arttttt.heroeslist.di.DaggerHeroesListFeatureComponent
import com.arttttt.heroeslist.di.HeroesListFeatureComponent
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

object HeroesListComponentHolder : ComponentHolder<HeroesListFeatureApi, HeroesListFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<HeroesListFeatureApi, HeroesListFeatureDependencies, HeroesListFeatureComponent> { dependencies ->
        DaggerHeroesListFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): HeroesListFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): HeroesListFeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
