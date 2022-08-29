plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.arttttt.swapidecompose"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.COMPILER_VERSION
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.AndroidX.coreKtx)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Lifecycle.lifecycleRuntimeKtx)
    implementation(Dependencies.Compose.activity)

    debugImplementation(Dependencies.Compose.uiTooling)

    implementation(Dependencies.Decompose.decompose)

    implementation(Dependencies.MviKotlin.mvikotlin)
    implementation(Dependencies.MviKotlin.mvikotlinMain)
    implementation(Dependencies.MviKotlin.mvikotlinExtensionsCoroutines)
    implementation(Dependencies.MviKotlin.mvikotlinRx)

    implementation(Dependencies.Coroutines.coroutinesCore)
    implementation(Dependencies.Coroutines.coroutinesAndroid)
}
