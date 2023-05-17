rootProject.name = "SWApiDecomposeKMM"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("multiplatform") version "1.8.20"
        id("com.android.library") version "7.4.2"
        id("com.android.application") version "7.4.2"
        kotlin("android") version "1.8.20"
        kotlin("plugin.serialization") version "1.8.20"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
include(":androidApp")
include(":shared:core:arch")
include(":shared:core:moduleinjector")
include(":shared:feature:root")
include(":shared:feature:heroeslist:api")
include(":shared:feature:heroeslist:impl")
include(":shared:feature:hero:api")
include(":shared:feature:hero:impl")
include(":iosframework")
