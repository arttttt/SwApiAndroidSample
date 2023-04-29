package com.arttttt.moduleinjector

fun<D : BaseFeatureDependencies> dependencyHolder0(
    block: (BaseDependencyHolder<D>) -> D
): D {
    return object : DependencyHolder0<D>() {

        override val block: (BaseDependencyHolder<D>) -> D = block
    }.dependencies
}
