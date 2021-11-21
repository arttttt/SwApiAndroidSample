package com.arttttt.main

import com.arttttt.main.di.DaggerMainFeatureComponent
import com.arttttt.main.di.MainFeatureComponent
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

object MainFeatureComponentHolder : ComponentHolder<MainFeatureApi, MainFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<MainFeatureApi, MainFeatureDependencies, MainFeatureComponent> { dependencies ->
        DaggerMainFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): MainFeatureApi {
        return componentHolderDelegate.get()
    }

    internal fun getComponent(): MainFeatureComponent {
        return componentHolderDelegate.getComponentImpl()
    }
}
