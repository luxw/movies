plugins {
    id(GradlePlugins.AndroidLibrary)
    id(GradlePlugins.KotlinAndroid)
    id(GradlePlugins.KotlinAndroidExtensions)
    id(GradlePlugins.KotlinKapt)
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

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(Dependencies.android.timber)

    api(Dependencies.android.rxJava)
    api(Dependencies.android.rxKotlin)
    api(Dependencies.android.retrofit)
    api(Dependencies.android.dagger)

    api(Dependencies.android.moshi)
    api(Dependencies.android.moshiAdapter)
    api(Dependencies.android.httpLogging)
    api(Dependencies.android.rxJavaAdapter)

    api(Dependencies.android.material)

    kapt(Dependencies.android.daggerCompiler)

    addTestDependencies()
}