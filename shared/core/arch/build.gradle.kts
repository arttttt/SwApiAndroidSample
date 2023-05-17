plugins {
    kotlin("multiplatform")
}

configureKMM()

configureAndroid {
    namespace = "com.arttttt.arch"
    isComposeEnabled = true
    isParcelizeEnabled = true
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "arch"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Dependencies.Decompose.decompose)

                api(Dependencies.MviKotlin.mvikotlin)
                api(Dependencies.MviKotlin.mvikotlinMain)
                api(Dependencies.MviKotlin.mvikotlinExtensionsCoroutines)
                api(Dependencies.MviKotlin.mvikotlinRx)
                api(Dependencies.MviKotlin.mvikotlinLogging)
                api(Dependencies.MviKotlin.mvikotlinTimetravel)

                api(Dependencies.Essenty.lifecycle)
                api(Dependencies.Essenty.parcelable)
                api(Dependencies.Essenty.instanceKeeper)
                api(Dependencies.Essenty.stateKeeper)
                api(Dependencies.Essenty.backHandler)

                implementation(Dependencies.Coroutines.core)

                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
            }
        }
        val commonTest by getting

        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Compose.ui)
                implementation(Dependencies.Compose.foundation)
                implementation(Dependencies.Decompose.decomposeComposeExtensions)
            }
        }
        val androidUnitTest by getting

        val jvmMain by getting {
            dependsOn(commonMain)
        }
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
    }
}
