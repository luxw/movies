plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

apply(from = "$rootDir/config/quality.gradle.kts")

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        applicationId = "com.example.currencyconverter"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        versionCode = Versions.versionCode
        versionName = Versions.versionName
        project.ext.set("versionName", versionName)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

base {
    archivesBaseName = "CurrencyConverter"
}

dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.android.appCompat)
    implementation(Dependencies.android.constraintLayout)

    addTestDependencies()
}