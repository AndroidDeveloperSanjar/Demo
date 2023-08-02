import Libs.Koin.koin

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
}

android {
    namespace = AppConfig.namespace("core.dispatchers")
}

dependencies {
    implementation(Libs.Kotlin.Coroutines.core)
    koin()
}