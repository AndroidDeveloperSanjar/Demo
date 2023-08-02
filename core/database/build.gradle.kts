import Libs.AndroidX.roomX
import Libs.Koin.koin

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

android {
    namespace = AppConfig.namespace("core.database")
}

dependencies {
    implementation(project(":core:dispatchers"))

    koin()
    implementation(Libs.sqlcipher)
    roomX()
    implementation("${Libs.Network.gsonGroupId}:gson:${Versions.Network.gson}")
}
