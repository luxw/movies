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
    const val gradle = "3.5.0"

    // Main dependencies
    const val kotlin = "1.3.50"
    const val appCompat = "1.1.0"
    const val constraintLayout = "2.0.0-beta1"
    const val timber = "4.7.1"
    const val koin = "2.0.1"

    // Testing dependencies
    const val testJUnit = "4.12"

    // Instrumented testing dependencies
    const val testRunner = "1.2.0"
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
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

/**
 * Describes test related dependencies.
 */
object TestDependencies {
    const val jUnit = "junit:junit:${Versions.testJUnit}"
    const val runner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.testEspressoCore}"
    const val ktlintTest = "com.pinterest.ktlint:ktlint-test:${Versions.ktlint}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
}

/**
 * Describes gradle specific dependencies.
 */
object GradleDependencies {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

/**
 * Describes quality dpeendencies.
 */
object QualityDependencies {
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    const val ktlintCore = "com.pinterest.ktlint:ktlint-core:${Versions.ktlint}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
}