plugins {
    id("com.android.library")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Dependencies.android.timber)

    api(Dependencies.android.rxJava)
    api(Dependencies.android.rxKotlin)
    api(Dependencies.android.retrofit)

    implementation(Dependencies.android.moshi)
    implementation(Dependencies.android.moshiAdapter)
    implementation(Dependencies.android.httpLogging)
    implementation(Dependencies.android.rxJavaAdapter)
}