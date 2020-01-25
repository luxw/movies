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
            proguardFiles(rootProject.file("movies/proguard-rules.pro"))
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

    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        // If we don't, it does not work: "unresolved reference: jvmTarget"
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.dsl.KotlinCompile::class).all {
    kotlinOptions {
        allWarningsAsErrors = true
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
