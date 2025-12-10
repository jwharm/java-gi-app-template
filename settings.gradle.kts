pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("build/repository") // used by flatpak-builder
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "java-gi-app-example"