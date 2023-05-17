package com.arttttt.moduleinjector

interface BaseFeatureDependencies

interface BaseFeatureApi

abstract class ComponentHolder<A : BaseFeatureApi, D : BaseFeatureDependencies> {
    abstract var dependencyProvider: (() -> D)?
    abstract val api: A
}

