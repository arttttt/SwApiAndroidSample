plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

configureKMM()

configureAndroid {
    namespace = "com.arttttt.root"
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
            baseName = "app"
        }
    }*/

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":moduleinjector"))
                implementation(project(":shared:arch"))
                implementation(project(":shared:heroeslist:api"))
                implementation(project(":shared:heroeslist:impl"))
                implementation(project(":shared:hero:api"))
                implementation(project(":shared:hero:impl"))

                implementation(Dependencies.Coroutines.core)
                implementation(Dependencies.Decompose.decomposeComposeExtensions)
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
            }
        }

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
