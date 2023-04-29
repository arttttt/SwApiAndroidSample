import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidConfig(
    var namespace: String? = null,
    var isComposeEnabled: Boolean = false,
    var isParcelizeEnabled: Boolean = false,
)

fun Project.configureAndroid(
    block: AndroidConfig.() -> Unit
) {
    val defaultSettings = requireSettings()

    AndroidConfig()
        .apply(block)
        .apply {
            plugins.apply("com.android.library")

            if (isParcelizeEnabled) {
                plugins.apply("kotlin-parcelize")
            }

            extensions.configure(BaseExtension::class) {
                this.namespace = this@apply.namespace ?: throw IllegalArgumentException("namespace is null")

                compileSdkVersion(defaultSettings.android.compileSdkVersion)

                defaultConfig {
                    minSdk = defaultSettings.android.minSdkVersion
                    targetSdk = defaultSettings.android.targetSdkVersion
                }

                compileOptions {
                    sourceCompatibility(JavaVersion.VERSION_1_8)
                    targetCompatibility(JavaVersion.VERSION_1_8)
                }

                if (isComposeEnabled) {
                    buildFeatures.compose = true

                    composeOptions {
                        kotlinCompilerExtensionVersion = Dependencies.Compose.COMPILER_VERSION
                    }
                }
            }
        }
}
