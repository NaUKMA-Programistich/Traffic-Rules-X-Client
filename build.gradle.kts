import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.0"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                // ktor
                implementation(libs.ktor.core)
                implementation(libs.ktor.log)
                implementation(libs.ktor.engine)
                implementation(libs.ktor.content)
                implementation(libs.ktor.serialization)

                // serialization
                implementation(libs.kotlin.serialization)

                // ViewModel
                implementation(libs.kviewmodel.core)
                implementation(libs.kviewmodel.compose)
                implementation(libs.kviewmodel.odyssey)

                // Navigation
                implementation(libs.odyssey.core)
                implementation(libs.odyssey.compose)

                implementation(libs.compose.markdown)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Traffic-Rules-X-Client"
            packageVersion = "1.0.0"
        }
    }
}
