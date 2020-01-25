plugins {
    id(GradlePlugins.AndroidDynamicFeature)
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
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

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

    buildTypes {
        val apiKey = "\"f393f12976e6ab8c677bd89bafe2a1c5\""
        val fieldName = "API_KEY"

        getByName("debug") {
            buildConfigField("String", fieldName, apiKey)
        }

        getByName("release") {
            buildConfigField("String", fieldName, apiKey)

            isDebuggable = false
            isZipAlignEnabled = true
        }
    }

    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
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
    implementation(Dependencies.android.paging)
    implementation(Dependencies.android.pagingRxJava)
    implementation(Dependencies.android.roomRuntime)
    implementation(Dependencies.android.roomRxJava)

    kapt(Dependencies.android.roomCompiler)

    addTestDependencies()
}
