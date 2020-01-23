/**
 * Describes all dependency versions.
 */
object Versions {
    // App version
    const val versionCode = 1
    const val versionName = "1.0.0"

    // Android version
    const val compileSdk = 29
    const val targetSdk = 29
    const val minSdk = 21
    const val buildTools = "29.0.2"

    // Build dependencies
    const val gradle = "3.5.3"

    // Main dependencies
    const val kotlin = "1.3.50"
    const val appCompat = "1.1.0"
    const val constraintLayout = "2.0.0-beta1"
    const val lifecycle = "2.1.0"
    const val timber = "4.7.1"
    const val rxJava = "2.2.16"
    const val rxAndroid = "2.1.1"
    const val rxJavaAdapter = "2.7.1"
    const val rxKotlin = "2.4.0"
    const val retrofit = "2.6.1"
    const val moshi = "1.9.2"
    const val httpLogging = "4.3.1"
    const val koin = "2.0.1"
    const val material = "1.0.0"
    const val glide = "4.10.0"
    const val navigation = "2.1.0"
    const val room = "2.2.3"
    const val paging = "2.1.1"

    // Testing dependencies
    const val testJUnit = "4.12"
    const val mockk = "1.9.1"
    const val kluent = "1.44"
    const val archCore = "2.0.0"

    // Instrumented testing dependencies
    const val testExtJUnit = "1.1.1"
    const val testCore = "1.2.0"
    const val testEspressoCore = "3.2.0"

    // Quality tools dependencies
    const val ktlint = "0.35.0"
    const val detekt = "1.1.1"
}

/**
 * Describes all dependencies.
 */
object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val gradle = GradleDependencies
    val android = AndroidDependencies
    val test = TestDependencies
    val quality = QualityDependencies
}

/**
 * Describes the Android application dependencies.
 */
object AndroidDependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxJavaAdapter}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val moshiAdapter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val httpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.httpLogging}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val pagingRxJava = "androidx.paging:paging-rxjava2:${Versions.paging}"

    val koin = Koin
    object Koin {
        const val androidCore = "org.koin:koin-android:${Versions.koin}"
        const val androidXScope = "org.koin:koin-androidx-scope:${Versions.koin}"
        const val androidXViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    }
}

/**
 * Describes test related dependencies.
 */
object TestDependencies {
    const val jUnit = "junit:junit:${Versions.testJUnit}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.testExtJUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.testEspressoCore}"
    const val core = "androidx.test:core:${Versions.testCore}"
    const val archCore = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val kluentAndroid = "org.amshove.kluent:kluent-android:${Versions.kluent}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
}

/**
 * Describes gradle specific dependencies.
 */
object GradleDependencies {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

/**
 * Describes quality dependencies.
 */
object QualityDependencies {
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
}