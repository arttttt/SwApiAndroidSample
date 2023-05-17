plugins {
    kotlin("multiplatform")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            export(project(":shared:core:arch"))
            export(project(":shared:core:moduleinjector"))
            export(project(":shared:feature:hero:api"))
            export(project(":shared:feature:hero:impl"))
            export(project(":shared:feature:heroeslist:api"))
            export(project(":shared:feature:heroeslist:impl"))
            export(project(":shared:feature:root"))

            export(Dependencies.Decompose.decompose)
            export(Dependencies.Essenty.lifecycle)
            export(Dependencies.Essenty.instanceKeeper)
            export(Dependencies.Essenty.stateKeeper)
            export(Dependencies.Essenty.backHandler)
            export(Dependencies.Coroutines.core)
            export("com.arkivanov.parcelize.darwin:runtime:0.1.4")
        }
    }

    sourceSets {
        val commonMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                api(project(":shared:core:arch"))
                api(project(":shared:core:moduleinjector"))
                api(project(":shared:feature:hero:api"))
                api(project(":shared:feature:hero:impl"))
                api(project(":shared:feature:heroeslist:api"))
                api(project(":shared:feature:heroeslist:impl"))
                api(project(":shared:feature:root"))

                api(Dependencies.Decompose.decompose)
                api(Dependencies.Essenty.lifecycle)
                api(Dependencies.Essenty.instanceKeeper)
                api(Dependencies.Essenty.stateKeeper)
                api(Dependencies.Essenty.backHandler)
                api(Dependencies.Coroutines.core)
                api("com.arkivanov.parcelize.darwin:runtime:0.1.4")
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}