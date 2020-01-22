plugins {
    id(GradlePlugins.AndroidDynamicFeature)
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

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isZipAlignEnabled = true
        }
    }
}

dependencies {
    // App module dependency
    implementation(project(":app"))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.android.appCompat)
    implementation(Dependencies.android.constraintLayout)
    implementation(Dependencies.android.lifecycle)
    implementation(Dependencies.android.lifecycleViewModel)
    implementation(Dependencies.android.glide)

    kapt(Dependencies.android.daggerCompiler)

    addTestDependencies()
}
