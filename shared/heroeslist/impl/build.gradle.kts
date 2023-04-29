plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

configureKMM()

configureAndroid {
    namespace = "com.arttttt.heroeslist.impl"
    isComposeEnabled = true
    isParcelizeEnabled = true
}

kotlin {
    /*listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "impl"
        }
    }*/

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":moduleinjector"))

                implementation(project(":shared:arch"))
                implementation(project(":shared:heroeslist:api"))

                implementation(Dependencies.Coroutines.core)
                implementation(Dependencies.Decompose.decomposeComposeExtensions)

                implementation("io.ktor:ktor-client-core:2.3.0")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")

                implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
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
                //debugImplementation(Dependencies.Compose.uiTooling)

                implementation("io.ktor:ktor-client-android:2.3.0")
            }
        }
        val androidUnitTest by getting

        val jvmMain by getting {
            dependsOn(commonMain)
        }

        /*val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
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
        }*/
    }
}
