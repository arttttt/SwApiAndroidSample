package com.arttttt.moduleinjector

interface BaseDependencyHolder<D : BaseFeatureDependencies> {
    val dependencies: D
}

abstract class DependencyHolder0<D : BaseFeatureDependencies>(
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <D : BaseFeatureDependencies> invoke(block: (BaseDependencyHolder<D>) -> D): BaseDependencyHolder<D> {
            return object : DependencyHolder0<D>() {
                override val block = block
            }
        }
    }

    abstract val block: (BaseDependencyHolder<D>) -> D

    override val dependencies: D
        get() = block(this)
}

abstract class DependencyHolder1<A1 : BaseFeatureApi, D : BaseFeatureDependencies>(
        private val api1: A1,
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <A1 : BaseFeatureApi, D : BaseFeatureDependencies> invoke(
                api1: A1,
                block: (BaseDependencyHolder<D>, A1) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder1<A1, D>(
                    api1 = api1
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (BaseDependencyHolder<D>, A1) -> D

    override val dependencies: D
        get() = block(this, api1)
}

abstract class DependencyHolder2<A1 : BaseFeatureApi, A2 : BaseFeatureApi, D : BaseFeatureDependencies>(
        private val api1: A1,
        private val api2: A2,
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <A1 : BaseFeatureApi, A2 : BaseFeatureApi, D : BaseFeatureDependencies> invoke(
                api1: A1,
                api2: A2,
                block: (BaseDependencyHolder<D>, A1, A2) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder2<A1, A2, D>(
                    api1 = api1,
                    api2 = api2
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (BaseDependencyHolder<D>, A1, A2) -> D

    override val dependencies: D
        get() = block(this, api1, api2)
}

abstract class DependencyHolder3<A1 : BaseFeatureApi, A2 : BaseFeatureApi, A3 : BaseFeatureApi, D : BaseFeatureDependencies>(
        private val api1: A1,
        private val api2: A2,
        private val api3: A3,
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <A1 : BaseFeatureApi, A2 : BaseFeatureApi, A3 : BaseFeatureApi, D : BaseFeatureDependencies> invoke(
                api1: A1,
                api2: A2,
                api3: A3,
                block: (BaseDependencyHolder<D>, A1, A2, A3) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder3<A1, A2, A3, D>(
                    api1 = api1,
                    api2 = api2,
                    api3 = api3
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (dependencyHolder: BaseDependencyHolder<D>, A1, A2, A3) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3)
}

abstract class DependencyHolder4<A1 : BaseFeatureApi, A2 : BaseFeatureApi, A3 : BaseFeatureApi, A4 : BaseFeatureApi, D : BaseFeatureDependencies>(
        private val api1: A1,
        private val api2: A2,
        private val api3: A3,
        private val api4: A4
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <A1 : BaseFeatureApi, A2 : BaseFeatureApi, A3 : BaseFeatureApi, A4 : BaseFeatureApi, D : BaseFeatureDependencies> invoke(
                api1: A1,
                api2: A2,
                api3: A3,
                api4: A4,
                block: (BaseDependencyHolder<D>, A1, A2, A3, A4) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder4<A1, A2, A3, A4, D>(
                    api1 = api1,
                    api2 = api2,
                    api3 = api3,
                    api4 = api4
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (dependencyHolder: BaseDependencyHolder<D>, A1, A2, A3, A4) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3, api4)
}

abstract class DependencyHolder5<
        A1 : BaseFeatureApi,
        A2 : BaseFeatureApi,
        A3 : BaseFeatureApi,
        A4 : BaseFeatureApi,
        A5 : BaseFeatureApi,
        D : BaseFeatureDependencies>(
        private val api1: A1,
        private val api2: A2,
        private val api3: A3,
        private val api4: A4,
        private val api5: A5,
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <
                A1 : BaseFeatureApi,
                A2 : BaseFeatureApi,
                A3 : BaseFeatureApi,
                A4 : BaseFeatureApi,
                A5 : BaseFeatureApi,
                D : BaseFeatureDependencies> invoke(
                api1: A1,
                api2: A2,
                api3: A3,
                api4: A4,
                api5: A5,
                block: (BaseDependencyHolder<D>, A1, A2, A3, A4, A5) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder5<A1, A2, A3, A4, A5, D>(
                    api1 = api1,
                    api2 = api2,
                    api3 = api3,
                    api4 = api4,
                    api5 = api5
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (dependencyHolder: BaseDependencyHolder<D>, A1, A2, A3, A4, A5) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3, api4, api5)
}

abstract class DependencyHolder6<
        A1 : BaseFeatureApi,
        A2 : BaseFeatureApi,
        A3 : BaseFeatureApi,
        A4 : BaseFeatureApi,
        A5 : BaseFeatureApi,
        A6 : BaseFeatureApi,
        D : BaseFeatureDependencies>(
        private val api1: A1,
        private val api2: A2,
        private val api3: A3,
        private val api4: A4,
        private val api5: A5,
        private val api6: A6
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <
                A1 : BaseFeatureApi,
                A2 : BaseFeatureApi,
                A3 : BaseFeatureApi,
                A4 : BaseFeatureApi,
                A5 : BaseFeatureApi,
                A6 : BaseFeatureApi,
                D : BaseFeatureDependencies> invoke(
                api1: A1,
                api2: A2,
                api3: A3,
                api4: A4,
                api5: A5,
                api6: A6,
                block: (BaseDependencyHolder<D>, A1, A2, A3, A4, A5, A6) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder6<A1, A2, A3, A4, A5, A6, D>(
                    api1 = api1,
                    api2 = api2,
                    api3 = api3,
                    api4 = api4,
                    api5 = api5,
                    api6 = api6
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (dependencyHolder: BaseDependencyHolder<D>, A1, A2, A3, A4, A5, A6) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3, api4, api5, api6)
}

abstract class DependencyHolder7<
        A1 : BaseFeatureApi,
        A2 : BaseFeatureApi,
        A3 : BaseFeatureApi,
        A4 : BaseFeatureApi,
        A5 : BaseFeatureApi,
        A6 : BaseFeatureApi,
        A7 : BaseFeatureApi,
        D : BaseFeatureDependencies>(
        private val api1: A1,
        private val api2: A2,
        private val api3: A3,
        private val api4: A4,
        private val api5: A5,
        private val api6: A6,
        private val api7: A7
) : BaseDependencyHolder<D> {

    companion object {
        operator fun <
                A1 : BaseFeatureApi,
                A2 : BaseFeatureApi,
                A3 : BaseFeatureApi,
                A4 : BaseFeatureApi,
                A5 : BaseFeatureApi,
                A6 : BaseFeatureApi,
                A7 : BaseFeatureApi,
                D : BaseFeatureDependencies> invoke(
                api1: A1,
                api2: A2,
                api3: A3,
                api4: A4,
                api5: A5,
                api6: A6,
                api7: A7,
                block: (BaseDependencyHolder<D>, A1, A2, A3, A4, A5, A6, A7) -> D
        ): BaseDependencyHolder<D> {
            return object : DependencyHolder7<A1, A2, A3, A4, A5, A6, A7, D>(
                    api1 = api1,
                    api2 = api2,
                    api3 = api3,
                    api4 = api4,
                    api5 = api5,
                    api6 = api6,
                    api7 = api7
            ) {
                override val block = block
            }
        }
    }

    abstract val block: (dependencyHolder: BaseDependencyHolder<D>, A1, A2, A3, A4, A5, A6, A7) -> D

    override val dependencies: D
        get() = block(this, api1, api2, api3, api4, api5, api6, api7)
}
