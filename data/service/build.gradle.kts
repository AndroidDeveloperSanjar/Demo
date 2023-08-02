import Libs.Koin.koin
import Libs.Network.Retrofit.retrofit

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.parcelize)
}

android {
    namespace = AppConfig.namespace("data.service")
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:dispatchers"))
    implementation(project(":data:common"))
    koin()
    retrofit()
}