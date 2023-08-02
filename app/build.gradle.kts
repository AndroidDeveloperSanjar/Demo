import Libs.AndroidX.Camera.cameraX
import Libs.AndroidX.Lifecycle.lifecycleX
import Libs.AndroidX.Navigation.navigationComponentX
import Libs.AndroidX.fragmentX
import Libs.Koin.koin
import Libs.Network.OkHttp.okhttp
import Libs.Network.Retrofit.retrofit
import Libs.Tests.Pluto.pluto
import Libs.Tests.testLibs
import Libs.uiUtils
import Libs.widgets

plugins {
    id(Plugins.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.parcelize)
}

val versionName = getVersionName()
val versionCode = getNewVersionCode()

android {
    namespace = "uz.example.demo"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "uz.example.demo"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = versionCode
        versionName = versionName

        vectorDrawables.useSupportLibrary = true
        resourceConfigurations += mutableSetOf("en", "ru", "uz")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    experimentalProperties["android.experimental.self-instrumenting"] = true

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true

            val minifyEnabled = false
            isMinifyEnabled = minifyEnabled
            isShrinkResources = minifyEnabled
            /*configure<CrashlyticsExtension> {
                // to disable mapping file uploads (default=true if minifying)
                mappingFileUploadEnabled = minifyEnabled
            }*/

            buildConfigBool("DEVELOPER_MODE", true)
            buildConfigBool("TEST_FEATURES_ENABLED", true)

            //signingConfig = signingConfigs.getByName("debug")
        }

        create("qa") {
            versionNameSuffix = "(debug $versionCode)"
            isDebuggable = false
            val minifyEnabled = true
            isMinifyEnabled = minifyEnabled
            isShrinkResources = minifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            /*configure<CrashlyticsExtension> {
                // to disable mapping file uploads (default=true if minifying)
                mappingFileUploadEnabled = minifyEnabled
            }*/

            buildConfigBool("DEVELOPER_MODE", false)
            buildConfigBool("TEST_FEATURES_ENABLED", true)

            //signingConfig = signingConfigs.getByName("release")
            matchingFallbacks += listOf("release")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    bundle {
        language {
            enableSplit = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = AppConfig.CompileOptions.javaCompatibility
        targetCompatibility = AppConfig.CompileOptions.javaCompatibility
    }
    kotlinOptions {
        jvmTarget = AppConfig.CompileOptions.kotlinJvmTarget
    }
}

dependencies {
    coreLibraryDesugaring(Libs.desugarJdkLibs)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(project(":lingver"))
    implementation(project(":core:database"))
    implementation(project(":core:resourceprovider"))
    implementation(project(":core:dispatchers"))
    implementation(project(":core:network"))
    implementation(project(":data"))
    implementation(project(":data:common"))
    implementation(project(":core:sharedpreferences"))
    implementation(project(":util"))

    // Kotlin components
    implementation(Libs.Kotlin.serializationJson)
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Kotlin.Coroutines.android)

    // DI
    koin()

    // Network
    okhttp(isProfilerEnabled = true)
    retrofit()

    // Gson
    implementation("${Libs.Network.gsonGroupId}:gson:${Versions.Network.gson}")

    // AndroidX platform
    cameraX()
    lifecycleX()
    fragmentX()
    navigationComponentX()
    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.annotation)

    implementation(Libs.AndroidX.activity)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.appcompatResources)

    implementation(Libs.AndroidX.biometric)
    implementation(Libs.AndroidX.paging3)
    implementation(Libs.AndroidX.sqlite)
    implementation(Libs.AndroidX.splashScreen)
    implementation(Libs.AndroidX.work)

    // UI
    implementation(Libs.Google.material)
    uiUtils()
    widgets()

    pluto()
    testLibs()
}