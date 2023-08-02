import Libs.Koin.koin
import Libs.Network.OkHttp.okhttp
import Libs.Network.Retrofit.retrofit

plugins {
    id(Plugins.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Kotlin.parcelize)
}

android {
    namespace = AppConfig.namespace("data")

//    defaultConfig {
//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments["room.schemaLocation"] = "$projectDir/schemas"
//            }
//        }
//    }
}

dependencies {
    implementation(project(":core:dispatchers"))
    implementation(project(":data:common"))
    //implementation(project(":core:database"))

    implementation(Libs.AndroidX.annotation)

    implementation(Libs.Kotlin.serializationJson)

    koin()

    okhttp(isProfilerEnabled = true)
    retrofit()

    implementation("${Libs.Network.gsonGroupId}:gson:${Versions.Network.gson}")

    testImplementation(Libs.Tests.JUnit.junit4)
    androidTestImplementation(Libs.Tests.UI.Espresso.core)
    androidTestImplementation(Libs.Tests.UI.junitExt)
}