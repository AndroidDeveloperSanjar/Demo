import org.gradle.api.JavaVersion

object AppConfig {
    const val minSdk = 24
    const val targetSdk = 33
    const val compileSdk = 33

    object CompileOptions {
        val javaCompatibility = JavaVersion.VERSION_17
        val kotlinJvmTarget = JavaVersion.VERSION_17.toString()
    }

    object ApiHosts {
        const val prod = "" //TODO will be production url
        const val test = "" //TODO will be test url
    }

    fun namespace(moduleName: String) = "com.example.android.$moduleName"
}
