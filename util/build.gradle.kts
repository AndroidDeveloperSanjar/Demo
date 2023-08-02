import Libs.AndroidX.Navigation.navigationComponentX
import Libs.AndroidX.fragmentX

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
}

android {
    namespace = AppConfig.namespace("util")
}

dependencies {
    implementation(Libs.AndroidX.core)
    implementation(Libs.browser)
    fragmentX()
    navigationComponentX()
}
