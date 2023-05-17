package com.arttttt.moduleinjector

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ComponentHolderDelegate<A : BaseFeatureApi, D : BaseFeatureDependencies, C : A>(
    private val componentFactory: (D) -> C
) : ComponentHolder<A, D>() {

    private val mutex = Mutex()

    override val api: A
        get() = getComponentImpl()

    override var dependencyProvider: (() -> D)? = null

    private var component: C? = null

    fun getComponentImpl(): C {
        var component: C? = null

        runBlocking {
            mutex.withLock(this) {
                dependencyProvider?.let { dependencyProvider ->
                    component = this@ComponentHolderDelegate.component
                    if (component == null) {
                        component = componentFactory(dependencyProvider())
                        this@ComponentHolderDelegate.component = component
                    }
                } ?: throw IllegalStateException("dependencyProvider for component with factory $componentFactory is not initialized")
            }
        }

        return component ?: throw IllegalStateException("Component holder with component factory $componentFactory is not initialized")
    }
}
