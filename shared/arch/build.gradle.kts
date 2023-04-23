plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
                freeCompilerArgs = listOf(
                    "-Xcontext-receivers",
                )
            }
        }
    }

    jvm()

/*    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "arch"
        }
    }*/

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
/*
val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }*/
    }
}

android {
    namespace = "com.arttttt.arch"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.COMPILER_VERSION
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
