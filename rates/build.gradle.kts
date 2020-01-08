plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

apply(from = "$rootDir/config/quality.gradle.kts")

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.android.appCompat)
    implementation(Dependencies.android.constraintLayout)

    // Core module dependency
    implementation(project(":core"))

    // App module dependency
    implementation(project(":app"))

    addTestDependencies()
}
