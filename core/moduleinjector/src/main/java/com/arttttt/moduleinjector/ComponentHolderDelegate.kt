package com.arttttt.moduleinjector

import java.lang.ref.WeakReference

class ComponentHolderDelegate<A : BaseFeatureApi, D : BaseFeatureDependencies, C : A>(
    private val componentFactory: (D) -> C
) : ComponentHolder<A, D> {

    override var dependencyProvider: (() -> D)? = null

    private var componentWeakRef: WeakReference<C>? = null

    val component: C
        get() {
            var component: C? = null

            synchronized(this) {
                dependencyProvider?.let { dependencyProvider ->
                    component = componentWeakRef?.get()
                    if (component == null) {
                        component = componentFactory(dependencyProvider())
                        componentWeakRef = WeakReference(component)
                    }
                } ?: throw IllegalStateException("dependencyProvider for component with factory $componentFactory is not initialized")
            }

            return component ?: throw IllegalStateException("Component holder with component factory $componentFactory is not initialized")
        }

    override val api: A
        get() = component
}
