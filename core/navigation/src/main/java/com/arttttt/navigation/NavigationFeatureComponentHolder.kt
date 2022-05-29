package com.arttttt.navigation

import com.arttttt.moduleinjector.ComponentHolder
import com.arttttt.moduleinjector.ComponentHolderDelegate
import com.arttttt.navigation.di.DaggerNavigationFeatureComponent
import com.arttttt.navigation.di.NavigationFeatureComponent

object NavigationFeatureComponentHolder : ComponentHolder<NavigationFeatureApi, NavigationFeatureDependencies> {

    private val delegate = ComponentHolderDelegate<NavigationFeatureApi, NavigationFeatureDependencies, NavigationFeatureComponent> { dependencies ->
        DaggerNavigationFeatureComponent
            .factory()
            .create(dependencies)
    }

    override var dependencyProvider by delegate::dependencyProvider

    override val api by delegate::api
}
