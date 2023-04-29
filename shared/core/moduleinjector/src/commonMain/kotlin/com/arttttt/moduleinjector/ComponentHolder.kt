package com.arttttt.moduleinjector

interface BaseFeatureDependencies {
    val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies>
}

interface BaseFeatureApi

interface ComponentHolder<A : BaseFeatureApi, D : BaseFeatureDependencies> {
    var dependencyProvider: (() -> D)?
    val api: A
}

