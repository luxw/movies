import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addTestDependencies() {
    testImplementation(Dependencies.test.jUnit)
    testImplementation(Dependencies.test.core)
    testImplementation(Dependencies.test.archCore)
    testImplementation(Dependencies.test.kluent)
    testImplementation(Dependencies.test.kluentAndroid)
    testImplementation(Dependencies.test.mockk)
    testImplementation(Dependencies.test.mockkAndroid)
    androidTestImplementation(Dependencies.test.extJUnit)
    androidTestImplementation(Dependencies.test.espresso)
    androidTestImplementation(Dependencies.test.archCore)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL syntax in module\build.gradle.kts files.
 */
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)