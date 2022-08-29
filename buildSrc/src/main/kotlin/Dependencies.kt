object Dependencies {

    object AndroidX {

        val coreKtx = "androidx.core:core-ktx:1.8.0"
    }

    object Compose {

        const val COMPOSE_VERSION = "1.2.1"
        const val COMPILER_VERSION = "1.3.0"

        val ui = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        val material = "androidx.compose.material:material:$COMPOSE_VERSION"
        val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$COMPOSE_VERSION"
        val activity = "androidx.activity:activity-compose:1.5.1"

        val uiTooling = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION"
    }

    object Coroutines {

        private const val COROUTINES_VERSION = "1.6.4"

        val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
        val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
    }

    object Decompose {

        private const val DECOMPOSE_VERSION = "1.0.0-alpha-04"

        val decompose = "com.arkivanov.decompose:decompose:$DECOMPOSE_VERSION"
    }

    object Lifecycle {

        val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    }

    object MviKotlin {

        private const val MVI_KOTLIN_VERSION = "3.0.2"

        val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$MVI_KOTLIN_VERSION"
        val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$MVI_KOTLIN_VERSION"
        val mvikotlinExtensionsCoroutines = "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$MVI_KOTLIN_VERSION"
        val mvikotlinRx = "com.arkivanov.mvikotlin:rx:$MVI_KOTLIN_VERSION"
    }
}
