plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.arttttt.androidapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.arttttt.androidapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
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
    implementation(project(":moduleinjector"))
    implementation(project(":shared:root"))
    implementation(project(":shared:arch"))
    implementation(project(":shared:heroeslist:api"))
    implementation(project(":shared:heroeslist:impl"))
    implementation(project(":shared:hero:api"))
    implementation(project(":shared:hero:impl"))

    implementation(Dependencies.AndroidX.coreKtx)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Lifecycle.lifecycleRuntimeKtx)
    implementation(Dependencies.Compose.activity)

    debugImplementation(Dependencies.Compose.uiTooling)
}
