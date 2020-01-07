val mavenUser: String by project
val mavenPassword: String by project
val mavenLink: String by project

repositories {
    maven {
        credentials {
            username = mavenUser
            password = mavenPassword
        }
        url = uri(mavenLink)
    }
}

plugins {
    `kotlin-dsl`
}