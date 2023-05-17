object Dependencies {

    object AndroidX {

        val coreKtx = "androidx.core:core-ktx:1.9.0"
    }

    object Compose {

        const val COMPOSE_VERSION = "1.4.2"
        const val COMPILER_VERSION = "1.4.6"

        val ui = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        val foundation = "androidx.compose.foundation:foundation:$COMPOSE_VERSION"
        val material = "androidx.compose.material:material:$COMPOSE_VERSION"
        val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$COMPOSE_VERSION"
        val activity = "androidx.activity:activity-compose:1.7.1"

        val uiTooling = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION"
    }

    object Coroutines {

        private const val COROUTINES_VERSION = "1.7.0-RC"

        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
    }

    object Decompose {

        private const val DECOMPOSE_VERSION = "2.0.0-alpha-02"

        val decompose = "com.arkivanov.decompose:decompose:$DECOMPOSE_VERSION"
        val decomposeComposeExtensions = "com.arkivanov.decompose:extensions-compose-jetpack:$DECOMPOSE_VERSION"
    }

    object Lifecycle {

        val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    }

    object MviKotlin {

        private const val MVI_KOTLIN_VERSION = "3.2.0"

        val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$MVI_KOTLIN_VERSION"
        val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$MVI_KOTLIN_VERSION"
        val mvikotlinExtensionsCoroutines = "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$MVI_KOTLIN_VERSION"
        val mvikotlinRx = "com.arkivanov.mvikotlin:rx:$MVI_KOTLIN_VERSION"
        val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$MVI_KOTLIN_VERSION"
        val mvikotlinTimetravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$MVI_KOTLIN_VERSION"
    }

    object Essenty {

        private const val ESSENTY_VERSION = "1.1.0"

        val lifecycle = "com.arkivanov.essenty:lifecycle:$ESSENTY_VERSION"
        val parcelable = "com.arkivanov.essenty:parcelable:$ESSENTY_VERSION"
        val stateKeeper = "com.arkivanov.essenty:state-keeper:$ESSENTY_VERSION"
        val instanceKeeper = "com.arkivanov.essenty:instance-keeper:$ESSENTY_VERSION"
        val backHandler = "com.arkivanov.essenty:back-handler:$ESSENTY_VERSION"
    }
}
