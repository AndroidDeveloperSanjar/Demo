pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.google.com")
        maven("https://maven.sumsub.com/repository/maven-public/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.google.com")
        maven("https://maven.fabric.io/public")
        maven("https://maven.sumsub.com/repository/maven-public/")
    }
}

rootProject.name = "Demo"
include(":app")
include(":core")
include(":core:database")
include(":util")
include(":core:dispatchers")
include(":core:network")
include(":core:resourceprovider")
include(":core:sharedpreferences")
include(":data")
include(":data:common")
include(":data:service")
include(":lingver")
