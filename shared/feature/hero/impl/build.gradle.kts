plugins {
    kotlin("multiplatform")
}

configureAndroid {
    namespace = "com.arttttt.hero.impl"
    isComposeEnabled = true
    isParcelizeEnabled = true
}

configureKMM()

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "impl"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:core:arch"))
                implementation(project(":shared:core:moduleinjector"))
                implementation(project(":shared:feature:heroeslist:api"))
                implementation(project(":shared:feature:hero:api"))

                implementation(Dependencies.Coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.AndroidX.coreKtx)

                implementation(Dependencies.Compose.ui)
                implementation(Dependencies.Compose.material)
                implementation(Dependencies.Compose.toolingPreview)
                implementation(Dependencies.Lifecycle.lifecycleRuntimeKtx)
                implementation(Dependencies.Compose.activity)

                implementation(Dependencies.Compose.uiTooling)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("com.arkivanov.parcelize.darwin:runtime:0.1.4")
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
