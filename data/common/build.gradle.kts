import Libs.Network.Retrofit.retrofit

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.parcelize)
}

android {
    namespace = AppConfig.namespace("data.common")
}

dependencies {
    implementation(Libs.AndroidX.annotation)
    retrofit()
}