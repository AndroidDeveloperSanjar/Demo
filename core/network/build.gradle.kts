import Libs.Google.Firebase.firebase
import Libs.Koin.koin
import Libs.Network.OkHttp.okhttp
import Libs.Network.Retrofit.retrofit
import Libs.Tests.Pluto.pluto

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
}

android {
    namespace = AppConfig.namespace("core.network")

    defaultConfig {
        buildConfigString("HOST_PROD", AppConfig.ApiHosts.prod) //TODO have to will be host
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            buildConfigString("DEFAULT_API_TYPE", "PROD")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigString("DEFAULT_API_TYPE", "PROD")
            buildConfigString("HOST_TEST", AppConfig.ApiHosts.test)
        }
    }
}

dependencies {
    firebase(onlyCrashlitics = true)
    koin()
    okhttp(isProfilerEnabled = true)
    retrofit()
    pluto()
}