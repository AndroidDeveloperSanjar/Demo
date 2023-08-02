plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

val androidToolsVersion = "8.0.2"
dependencies {
    implementation("com.android.tools.build:gradle:$androidToolsVersion")
}