import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin)
    application
}

group = "com.github.callmephil1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    testImplementation(kotlin("test"))
}

application {
    val raylibLibrary = File("${projectDir.parentFile}\\raylib")
    mainClass.set("MainKt")
    applicationDefaultJvmArgs += listOf(
        "-Djava.library.path=$raylibLibrary",
        "--enable-native-access=ALL-UNNAMED"
    )
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)

    compilerOptions {
        jvmTarget = JvmTarget.JVM_22
    }
}