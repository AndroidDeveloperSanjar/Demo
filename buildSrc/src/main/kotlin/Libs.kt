import Libs.Tests.JUnit.junit4
import Libs.Tests.JUnit.jupiter
import Libs.Tests.UI.espresso
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Libs {
    const val assentRuntimePermissions =
        "com.afollestad.assent:core:${Versions.assentRuntimePermissions}"
    const val assentRuntimePermissionsRationales =
        "com.afollestad.assent:rationales:${Versions.assentRuntimePermissions}"
    const val assentRuntimePermissionsCoroutines =
        "com.afollestad.assent:coroutines:${Versions.assentRuntimePermissions}"
    const val browser = "androidx.browser:browser:${Versions.browser}"
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdkLibs}"
    const val sqlcipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlcipher}"

    object Koin {

        const val core = "io.insert-koin:koin-core:${Versions.Koin.core}"
        const val java = "io.insert-koin:koin-java:${Versions.Koin.core}"

        object AndroidX {
            const val android = "io.insert-koin:koin-android:${Versions.Koin.android}"
            const val javaCompatibility =
                "io.insert-koin:koin-android-compat:${Versions.Koin.android}"
            const val jetpackWorkManager =
                "io.insert-koin:koin-androidx-workmanager:${Versions.Koin.android}"
            const val navigationGraph =
                "io.insert-koin:koin-androidx-navigation:${Versions.Koin.android}"
        }

        object Test {
            const val koinTest = "io.insert-koin:koin-test:${Versions.Koin.core}"
            const val koinTestJUnit4 = "io.insert-koin:koin-test-junit4:${Versions.Koin.core}"
        }

        fun DependencyHandler.koin() {
            implementation(AndroidX.android)
            implementation(AndroidX.javaCompatibility)
//            implementation(AndroidX.jetpackWorkManager)
//            implementation(AndroidX.navigationGraph)

//            testImplementation(Test.koinTest)
            testImplementation(Test.koinTestJUnit4)
//            androidTestImplementation(Test.koinTest)
        }
    }

    object Kotlin {
        const val serializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serializationJson}"

        object Coroutines {
            const val core =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        }
    }

    object AndroidX {
        // core
        const val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val annotation = "androidx.annotation:annotation:${Versions.AndroidX.annotation}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.AndroidX.splashscreen}"

        // base components
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val appcompatResources =
            "androidx.appcompat:appcompat-resources:${Versions.AndroidX.appcompat}"
        const val activity = "androidx.activity:activity-ktx:${Versions.AndroidX.activity}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
        const val fragmentTesting =
            "androidx.fragment:fragment-testing:${Versions.AndroidX.fragment}"

        // features
        const val biometric = "androidx.biometric:biometric:${Versions.AndroidX.biometric}"
        const val crypto = "androidx.security:security-crypto:${Versions.AndroidX.crypto}"
        const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.AndroidX.paging3}"
        const val sqlite = "androidx.sqlite:sqlite-ktx:${Versions.AndroidX.sqlite}"
        const val paging3Common = "androidx.paging:paging-common-ktx:${Versions.AndroidX.paging3}"
        const val work = "androidx.work:work-runtime-ktx:${Versions.AndroidX.work}"

        // widgets
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val coordinatorLayout =
            "androidx.coordinatorlayout:coordinatorlayout:${Versions.AndroidX.coordinatorLayout}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swipeRefreshLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"
        const val cardView = "androidx.cardview:cardview:${Versions.AndroidX.cardView}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.AndroidX.viewPager2}"
        const val customView = "androidx.customview:customview:${Versions.AndroidX.customView}"
        const val interpolator =
            "androidx.interpolator:interpolator:${Versions.AndroidX.interpolator}"
        const val webkit = "androidx.webkit:webkit:${Versions.AndroidX.webkit}"

        fun DependencyHandler.fragmentX() {
            implementation(fragment)
            debugImplementation(fragmentTesting)
        }

        fun DependencyHandler.roomX() {
            val version = Versions.AndroidX.room
            val room = "androidx.room:room"
            val runtime = "$room-runtime:$version"

            implementation(runtime)
            implementation("$room-ktx:$version")
            kapt("$room-compiler:$version")
            testImplementation("$room-testing:$version")
        }

        object Camera {

            private const val version = Versions.AndroidX.camera
            private const val camera = "androidx.camera:camera"

            const val core = "$camera-core:$version"

            fun DependencyHandler.cameraX() {
                implementation(core)
                implementation("$camera-camera2:$version")
                implementation("$camera-lifecycle:$version")
                implementation("$camera-view:$version")
                implementation("$camera-extensions:$version")
            }
        }

        object Navigation {
            private const val version = Versions.AndroidX.navigation
            private const val navigation = "androidx.navigation:navigation"
            fun DependencyHandler.navigationComponentX() {
                implementation("$navigation-fragment-ktx:$version")
                implementation("$navigation-ui-ktx:$version")
            }
        }

        object Lifecycle {

            private const val version = Versions.AndroidX.lifecycle
            private const val lifecycle = "androidx.lifecycle:lifecycle"

            fun DependencyHandler.liveData() {
                implementation("$lifecycle-livedata:$version")
                implementation("$lifecycle-runtime-ktx:$version")
            }

            fun DependencyHandler.lifecycleX() {
                implementation("$lifecycle-viewmodel-ktx:$version") // for viewModelScope
                implementation("$lifecycle-livedata-ktx:$version") // livedata
                implementation("$lifecycle-runtime-ktx:$version") // for lifecycleScope
                implementation("$lifecycle-viewmodel-savedstate:$version")
                implementation("$lifecycle-common-java8:$version")
                implementation("$lifecycle-service:$version")
                implementation("$lifecycle-process:$version")
            }
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"

        /**
         * SplitInstallManager. Manages sessions for requesting and installing split APKs for
         * additional features or language resources. language resources will be reinstalled if necessary
         */
        // in-app review
        const val googlePlayCore = "com.google.android.play:core:${Versions.Google.playCore}"

        const val places = "com.google.android.libraries.places:places:${Versions.Google.places}"

        object Services {
            private const val googleServices = "com.google.android.gms:play-services"

            const val maps = "$googleServices-maps:${Versions.Google.servicesMaps}"
            const val location = "$googleServices-location:${Versions.Google.servicesLocation}"
            const val phoneAuth =
                "$googleServices-auth-api-phone:${Versions.Google.servicesPhoneAuth}" // sms verify api
            const val auth = "$googleServices-auth:${Versions.Google.servicesAuth}"
        }

        object MachineLearningKit {
            private const val mlkit = "com.google.mlkit"

            fun DependencyHandler.mlkit() {
                implementation("$mlkit:face-detection:${Versions.Google.mlkitFaceDetection}")
                implementation("$mlkit:text-recognition:${Versions.Google.mlkitTextRecognition}")
                implementation("$mlkit:barcode-scanning:${Versions.Google.mlkitBarcodeScanning}")
            }
        }

        object MapsAndroid {
            private const val maps = "com.google.maps.android"

            const val utils = "$maps:android-maps-utils:${Versions.Google.mapsAndroid}"
            const val coreKtx = "$maps:maps-ktx:${Versions.Google.mapsAndroid}"
            const val utilsKtx = "$maps:maps-utils-ktx:${Versions.Google.mapsAndroid}"
        }

        fun DependencyHandler.googleMaps() {
            implementation(Services.maps)
            implementation(Services.location)
            implementation(MapsAndroid.utils)
            implementation(MapsAndroid.coreKtx)
            implementation(MapsAndroid.utilsKtx)
        }

        object Firebase {
            private const val firebase = "com.google.firebase:firebase"

            /*разделить зависимости, чтоб не тягать в каждый модуль лишнее*/
            fun DependencyHandler.firebase(onlyCrashlitics: Boolean = false) {
                if (!onlyCrashlitics) {
                    implementation(platform("$firebase-bom:${Versions.Google.firebaseBom}"))/* implementation("$firebase-perf")*/
                    implementation("$firebase-crashlytics")
                    implementation("$firebase-analytics-ktx")
                    implementation("$firebase-dynamic-links-ktx")
                    implementation("$firebase-messaging")
                    implementation("$firebase-inappmessaging-display-ktx")
                    implementation("$firebase-installations")
                    implementation("$firebase-config-ktx")
                } else {
                    implementation("$firebase-crashlytics")
                    implementation(platform("$firebase-bom:${Versions.Google.firebaseBom}"))
                }
            }
        }
    }

    object Network {
        const val gsonGroupId = "com.google.code.gson"
        const val javaWebSocket =
            "org.java-websocket:Java-WebSocket:${Versions.Network.javaWebSocket}"

        object OkHttp {
            private const val okhttp = "com.squareup.okhttp3"
            fun DependencyHandler.okhttp(isProfilerEnabled: Boolean = false) {
                implementation(platform("$okhttp:okhttp-bom:${Versions.Network.okhttp}"))
                implementation("$okhttp:okhttp")
                implementation("$okhttp:logging-interceptor")
                if (isProfilerEnabled) {
                    implementation("com.localebro:okhttpprofiler:${Versions.Network.okhttpProfiler}")
                }
            }
        }

        object Retrofit {
            private const val version = Versions.Network.retrofit
            private const val retrofit = "com.squareup.retrofit2"

            fun DependencyHandler.retrofit() {
                implementation("$retrofit:retrofit:$version")
                implementation("$retrofit:converter-gson:$version")
                implementation("$retrofit:adapter-rxjava2:${Versions.Network.retrofitAdapterRxJava2}")
            }
        }
    }

    object Analytics {
        const val adjust =
            "com.adjust.sdk:adjust-android:${Versions.Analytics.adjust}" // + signaturev2 lib from file
        const val facebookSdk =
            "com.facebook.android:facebook-android-sdk:${Versions.Analytics.facebookSdk}"
        const val installReferrer =
            "com.android.installreferrer:installreferrer:${Versions.Analytics.installReferrer}"

        fun DependencyHandler.analyticLibs() {
            implementation(adjust)
            implementation(facebookSdk)
            implementation(installReferrer)
        }
    }

    fun DependencyHandler.coil() {
        implementation("io.coil-kt:coil:${Versions.UI.coil}")
        implementation("io.coil-kt:coil-gif:${Versions.UI.coilGif}")
    }

    // TODO:
    //  1. remove unused/unsupported/obsolete libs
    //  2. divide into logic groups or individual values to reuse in modules
    fun DependencyHandler.uiUtils() {
        //implementation("io.michaelrocks:libphonenumber-android:${Versions.UI.libPhoneNumber}")
        //implementation("net.yslibrary.keyboardvisibilityevent:keyboardvisibilityevent:${Versions.UI.keyboardVisibilityEvent}")

        facebookShimmer()

        // OverScrollDecoratorHelper
        //implementation("io.github.everythingme:overscroll-decor-android:${Versions.UI.overScrollDecorator}")

        // Hypertext in TextView
        //implementation("com.klinkerapps:link_builder:${Versions.UI.textLinkBuilder}")

        // View binding delegate
        implementation("com.github.kirich1409:viewbindingpropertydelegate-full:${Versions.UI.viewBindingDelegate}")

        // Image processing
        coil()
        //implementation("com.github.yalantis:ucrop:${Versions.UI.imageCrop}")

        //implementation("com.mig35:carousellayoutmanager:${Versions.UI.carouselLayoutManager}")
        //implementation(project(":legacyandrsupportlibs:StackLayoutManager"))
    }

    fun DependencyHandler.paris() {
        // Set style views programatically
        implementation("com.airbnb.android:paris:${Versions.UI.parisProgrammaticStyling}")
    }

    fun DependencyHandler.rmrInputMask() {
        implementation("com.redmadrobot:input-mask-android:${Versions.UI.rmrInputMask}")
    }

    fun DependencyHandler.facebookShimmer() {
        implementation("com.facebook.shimmer:shimmer:${Versions.UI.facebookShimmer}")
    }

    // TODO:
    //  1. remove unused/unsupported/obsolete libs
    //  2. divide into logic groups or individual values to reuse in modules
    fun DependencyHandler.widgets() {
        // AndroidX widgets
        implementation(AndroidX.constraintLayout)
        implementation(AndroidX.cardView)
        implementation(AndroidX.recyclerView)
        implementation(AndroidX.viewPager2)
        implementation(AndroidX.webkit)
    }

    object ThirdPart {

    }

    fun DependencyHandler.zXingAndroid() {
        implementation(project(":legacyandrsupportlibs:barcodescanner-master:core"))
        implementation(project(":legacyandrsupportlibs:barcodescanner-master:zxing"))
        implementation("com.journeyapps:zxing-android-embedded:4.3.0@aar")
        implementation("com.google.zxing:core:3.3.3")
    }

    object Tests {
        object LeakCanary {
            private const val version = Versions.Tests.leakCanary
            private const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$version"

            fun DependencyHandler.leakCanary() {
                debugImplementation(leakCanary)
            }
        }

        object Pluto {
            private const val version = Versions.Tests.pluto
            private const val pluto = "com.mocklets:pluto:$version"
            private const val pluto_no_op = "com.mocklets:pluto-no-op:$version"

            fun DependencyHandler.pluto() {
                debugImplementation(pluto)
                releaseImplementation(pluto_no_op)
            }
        }

        object Benchmark {
            const val profileinstaller =
                "androidx.profileinstaller:profileinstaller:${Versions.Tests.Benchmark.profileinstaller}"
            const val benchmark =
                "androidx.benchmark:benchmark-macro-junit4:${Versions.Tests.Benchmark.benchmark}"
        }

        object UI {
            const val baseVersion = Versions.Tests.UI.base
            const val core = "androidx.test:core:$baseVersion"
            const val rules = "androidx.test:core:$baseVersion"
            const val runner = "androidx.test:core:$baseVersion"
            const val uiautomator =
                "androidx.test.uiautomator:uiautomator:${Versions.Tests.UI.uiautomator}"
            const val hamcrest = "org.hamcrest:hamcrest-integration:${Versions.Tests.UI.hamcrest}"
            const val junitExt = "androidx.test.ext:junit:${Versions.Tests.UI.junitExt}"

            object Espresso {
                private const val espresso = "androidx.test.espresso:espresso"

                const val core = "$espresso-core:${Versions.Tests.UI.espresso}"
                const val idlingResource = "$espresso-idling-resource:${Versions.Tests.UI.espresso}"
            }

            fun DependencyHandler.espresso() {
                androidTestImplementation(Libs.AndroidX.annotation)
                androidTestImplementation(Espresso.core)
                androidTestImplementation(core)
                androidTestImplementation(rules)
                androidTestImplementation(runner)
                // UiAutomator and Hamcrest
                androidTestImplementation(uiautomator)
                androidTestImplementation(hamcrest)
            }
        }

        object JUnit {
            const val junit4 = "junit:junit:${Versions.Tests.junit4}"
            private const val version5 = Versions.Tests.junit5
            private const val jupiter = "org.junit.jupiter:junit-jupiter"

            fun DependencyHandler.jupiter(paramTestEnabled: Boolean = true) {
                // (Required) Writing and executing Unit Tests on the JUnit Platform
                testImplementation("$jupiter-api:$version5")
                testRuntimeOnly("$jupiter-engine:$version5")

                if (paramTestEnabled) {
                    // (Optional) If you need "Parameterized Tests"
                    testImplementation("$jupiter-params:$version5")
                }
            }

            fun DependencyHandler.junit4() {
                // (Optional) If you also have JUnit 4-based tests
                testImplementation(JUnit.junit4)
                testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$version5")
            }
        }

        fun DependencyHandler.testLibs() {
            espresso()
            jupiter()
            junit4()
        }
    }
}
