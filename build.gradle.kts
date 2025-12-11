plugins {
    application
    alias(libs.plugins.flatpak.generator)
}

repositories {
    mavenCentral()
    maven("build/repository") // used by flatpak-builder
}

application {
    applicationName = "example"
    mainClass = "org.domain.Example"
    applicationDefaultJvmArgs += "--enable-native-access=ALL-UNNAMED"
}

dependencies {
    implementation(libs.bundles.java.gi)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks {
    // Generate a file with all dependency urls for the offline flatpak build
    flatpakGradleGenerator.configure {
        outputFile = rootProject.layout.projectDirectory.file("data/maven-dependencies.json")
        downloadDirectory = "build/repository"
    }

    // This task will install into the "/app" directory.
    val install by registering(Copy::class) {
        from(installDist)
        into("/app")
    }
}
