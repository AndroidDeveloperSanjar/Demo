// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.application) apply false
    id(Plugins.library) apply false
    id(Plugins.Kotlin.android) version Versions.Kotlin.core apply false
    id(Plugins.Kotlin.parcelize) version Versions.Kotlin.core apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
}

subprojects {
    project.plugins.applyBaseConfig(project)
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is com.android.build.gradle.AppPlugin -> {
                project.extensions
                    .getByType<com.android.build.gradle.AppExtension>()
                    .apply {
                        commonConfig()
                    }
            }
            is com.android.build.gradle.internal.plugins.LibraryPlugin -> {
                project.extensions
                    .getByType<com.android.build.gradle.LibraryExtension>()
                    .apply {
                        commonConfig()
                        buildTypes {
                            release {
                                isMinifyEnabled = true
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    "proguard-rules.pro"
                                )
                                consumerProguardFiles("proguard-rules.pro")
                            }
                        }
                    }
            }
        }
    }
}

fun com.android.build.gradle.BaseExtension.commonConfig() {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig.apply {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions.apply {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(AppConfig.CompileOptions.javaCompatibility)
        targetCompatibility(AppConfig.CompileOptions.javaCompatibility)
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = AppConfig.CompileOptions.kotlinJvmTarget
        }
    }

    buildFeatures.buildConfig = true
}
