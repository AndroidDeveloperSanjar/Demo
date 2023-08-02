import Libs.Koin.koin

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
}

android {
    namespace = AppConfig.namespace("core.sharedpreferences")
}

dependencies {
    implementation(Libs.AndroidX.crypto)
    koin()
}
