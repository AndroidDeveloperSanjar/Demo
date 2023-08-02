object Versions {
    const val hilt = "2.42"
    const val assentRuntimePermissions = "3.0.2"
    const val sqlcipher = "4.5.1"
    const val tensorflow = "1.15.0" // for scancard
    const val browser = "1.4.0"
    const val desugarJdkLibs = "1.2.2"

    object Plugins {
        const val androidGradlePlugin = "7.4.0" // Actually declared in `/buildSrc/build.gradle.kts`
        const val junit5Android = "1.8.2.1"
        const val firebaseCrashlytics = "2.9.2"
        const val firebasePerformance = "1.4.2"
        const val googleServices = "4.3.15"
        const val safeArgs = "2.5.3"
        const val owaspChecker = "8.1.2"

        object StaticAnalyze {
            const val detekt = "1.22.0"
        }
    }

    object Kotlin {
        const val core = "1.8.21"
        const val serializationJson = "1.1.0"
        const val coroutines = "1.7.1"
    }

    object AndroidX {
        // core
        const val core = "1.10.1"
        const val annotation = "1.6.0"
        const val splashscreen = "1.0.0"

        // base components
        const val appcompat = "1.6.1"
        const val activity = "1.7.1"
        const val fragment = "1.5.7"
        const val lifecycle = "2.6.1"
        const val navigation = "2.5.3"

        // features
        const val room = "2.5.1"
        const val camera = "1.2.2"
        const val paging3 = "3.1.1"
        const val sqlite = "2.2.0"
        const val crypto = "1.1.0-alpha06"
        const val biometric = "1.1.0"
        const val work = "2.8.1"

        // widgets
        const val constraintLayout = "2.1.4"
        const val coordinatorLayout = "1.2.0"
        const val swipeRefreshLayout = "1.1.0"
        const val recyclerView = "1.2.1"
        const val cardView = "1.0.0"
        const val viewPager2 = "1.1.0-beta01"
        const val customView = "1.1.0"
        const val interpolator = "1.0.0"
        const val webkit = "1.7.0"
    }

    object Google {
        const val playCore = "1.10.3"
        const val material = "1.9.0"
        const val places = "3.0.0"
        const val servicesMaps = "18.1.0"
        const val servicesLocation = "21.0.1"
        const val servicesPhoneAuth = "18.0.1"
        const val servicesAuth = "20.2.0"
        const val firebaseBom = "32.0.0"
        const val mlkitFaceDetection = "16.1.5"
        const val mlkitTextRecognition = "16.0.0-beta6"
        const val mlkitBarcodeScanning = "17.1.0"
        const val mapsAndroid = "3.4.0"
    }

    object Network {
        const val okhttp = "5.0.0-alpha.7"
        const val okhttpProfiler = "1.0.8"
        const val retrofit = "2.9.0"
        const val retrofitAdapterRxJava2 = "2.6.1"
        const val javaWebSocket = "1.5.2"
        const val gson = "2.9.0"
    }

    object Analytics {
        const val adjust = "4.33.0"
        const val facebookSdk = "9.0.0"
        const val installReferrer = "2.2"
    }

    object UI {
        const val coil = "1.4.0"
        const val coilGif = "1.1.1"

        const val libPhoneNumber = "8.13.13"
        const val rmrInputMask = "6.1.0"
        const val textLinkBuilder = "2.0.5"
        const val facebookShimmer = "0.5.0"
        const val overScrollDecorator = "1.1.1"
        const val keyboardVisibilityEvent = "3.0.0-RC3"
        const val imageCrop = "2.2.8"

        const val viewBindingDelegate = "1.5.8"
        const val carouselLayoutManager = "1.4.6"
        const val parisProgrammaticStyling = "2.0.1"
    }

    object Tests {
        const val leakCanary = "2.10"
        const val pluto = "1.1.3"
        const val junit4 = "4.13.2"
        const val junit5 = "5.7.1"

        object UI {
            const val base = "1.4.0"
            const val espresso = "3.5.1"
            const val junitExt = "1.1.5"
            const val uiautomator = "2.2.0"
            const val hamcrest = "1.3"
        }

        object Benchmark {
            const val profileinstaller = "1.3.0"
            const val benchmark = "1.1.1"
        }
    }

    object MyId {
        const val ktor = "2.3.0"
        const val sentry = "6.16.0"
    }

    object Koin {
        const val core = "3.4.0"
        const val android = "3.4.0"
    }
}
