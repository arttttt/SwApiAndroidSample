plugins {
    kotlin("multiplatform") apply false
    id("com.android.library") apply false
    kotlin("plugin.serialization") apply false
}

setup(
    android = {
        compileSdkVersion = 33
        minSdkVersion = 24
        targetSdkVersion = 33
    },
    kmm = {
        android()
        jvm()
    }
)
