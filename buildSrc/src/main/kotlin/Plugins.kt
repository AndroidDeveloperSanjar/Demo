object Plugins {
    const val application = "com.android.application" // version Versions.Plugins.androidGradlePlugin
    const val library = "com.android.library" // version Versions.Plugins.androidGradlePlugin
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.Plugins.androidGradlePlugin}"

    const val googleServices = "com.google.gms.google-services"

    const val owaspChecker = "org.owasp.dependencycheck"

    object Kotlin {
        private const val kotlin = "org.jetbrains.kotlin"

        const val android = "$kotlin.android"
        const val kapt = "$kotlin.kapt"
        const val parcelize = "$kotlin.plugin.parcelize"
    }

    object Firebase {
        const val crashlytics = "com.google.firebase.crashlytics"
        const val performance = "com.google.firebase.firebase-perf"
    }

    object AndroidX {
        const val safeArgs = "androidx.navigation.safeargs.kotlin"
    }

    object Tests {
        const val junit5Android = "de.mannodermaus.android-junit5"
    }

    object StaticAnalyze {
        const val detekt = "io.gitlab.arturbosch.detekt"
        const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting"
    }
}