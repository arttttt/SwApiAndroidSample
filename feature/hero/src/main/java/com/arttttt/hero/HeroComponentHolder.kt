package com.arttttt.hero

import com.arttttt.hero.di.DaggerHeroFeatureComponent
import com.arttttt.hero.di.HeroFeatureComponent
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

object HeroComponentHolder : ComponentHolder<HeroFeatureApi, HeroFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<HeroFeatureApi, HeroFeatureDependencies, HeroFeatureComponent> { dependencies ->
        DaggerHeroFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): HeroFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): HeroFeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
