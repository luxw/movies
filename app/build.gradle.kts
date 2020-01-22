plugins {
    id(GradlePlugins.AndroidApplication)
    id(GradlePlugins.KotlinAndroid)
    id(GradlePlugins.KotlinAndroidExtensions)
    id(GradlePlugins.KotlinKapt)
    id(GradlePlugins.SafeArgs)
}

apply(from = "$rootDir/config/quality.gradle.kts")

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        applicationId = "com.mfinatti.matheusmovies"
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

    dynamicFeatures = mutableSetOf(":movies")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding {
        isEnabled = true
    }
}

base {
    archivesBaseName = "MatheusMovies"
}

dependencies {
    // Core module dependency
    api(project(":core"))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.android.appCompat)
    implementation(Dependencies.android.constraintLayout)

    api(Dependencies.android.rxAndroid)

    addTestDependencies()
}
