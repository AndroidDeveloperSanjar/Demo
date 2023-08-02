plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
}

android {
    namespace = AppConfig.namespace("core.resourceprovider")
}

dependencies {
    implementation(Libs.AndroidX.annotation)
}