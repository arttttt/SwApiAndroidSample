package com.arttttt.navigation

import com.arttttt.navigation.di.DaggerNavigationFeatureComponent
import com.arttttt.navigation.di.NavigationFeatureComponent
import com.ewa.module_injector.ComponentHolder
import com.ewa.module_injector.ComponentHolderDelegate

object NavigationComponentHolder : ComponentHolder<NavigationFeatureApi, NavigationFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<NavigationFeatureApi, NavigationFeatureDependencies, NavigationFeatureComponent> { dependencies ->
        DaggerNavigationFeatureComponent
            .factory()
            .create(
                dependencies = dependencies
            )
    }

    override var dependencyProvider by componentHolderDelegate::dependencyProvider

    override fun get(): NavigationFeatureApi {
        return componentHolderDelegate.get()
    }
}
