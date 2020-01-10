plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
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

    signingConfigs {
        // Hard-coded values for obvious reasons.
        create("release") {
            storeFile = rootProject.file("release.keystore")
            storePassword = "mercari"
            keyAlias = "key0"
            keyPassword = "mercari"
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isZipAlignEnabled = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            val files = rootProject.file("config/proguard/")
                .listFiles()
                ?.filter { it.name.startsWith("proguard") }
                ?.toTypedArray()

            files?.let { proguardFiles(*it) }

            signingConfig = signingConfigs.getByName("release")
        }
    }

    dynamicFeatures = mutableSetOf(":rates")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding {
        isEnabled = true
    }
}

base {
    archivesBaseName = "CurrencyConverter"
}

dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.android.appCompat)
    implementation(Dependencies.android.constraintLayout)

    api(Dependencies.android.rxAndroid)

    // Core module dependency
    implementation(project(":core"))

    kapt(Dependencies.android.daggerCompiler)

    addTestDependencies()
}
