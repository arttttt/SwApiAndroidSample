import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.kotlin.dsl.extra
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

private const val EXTRA_PARAM = "project_settings"

class AndroidConfigInit(
    var compileSdkVersion: Int? = null,
    var minSdkVersion: Int? = null,
    var targetSdkVersion: Int? = null,
)

class ProjectSettings(
    val android: Android,
    val kmm: KMM,
) {

    class Android(
        var compileSdkVersion: Int,
        var minSdkVersion: Int,
        var targetSdkVersion: Int,
    )

    fun interface KMM {
        operator fun KotlinMultiplatformExtension.invoke()
    }
}

fun Project.setup(
    android: AndroidConfigInit.() -> Unit,
    kmm: KotlinMultiplatformExtension.() -> Unit,
) {
    val init = AndroidConfigInit().apply(android)

    val settings = ProjectSettings(
        android = ProjectSettings.Android(
            compileSdkVersion = init.compileSdkVersion!!,
            minSdkVersion = init.minSdkVersion!!,
            targetSdkVersion = init.targetSdkVersion!!,
        ),
        kmm = kmm,
    )

    extra[EXTRA_PARAM] = settings
}

fun Project.requireSettings(): ProjectSettings {
    return requireNotNull(getRootExtra()[EXTRA_PARAM]) {
        "project settings not specified"
    } as ProjectSettings
}

private fun Project.getRootExtra(): ExtraPropertiesExtension {
    return when {
        rootProject == this -> extra
        else -> rootProject.getRootExtra()
    }
}
