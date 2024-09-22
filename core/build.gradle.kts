import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.kotlin)
    application
}

base {
    archivesName = "kraylib-core"
}

group = "com.github.callmephil1"
version = generateVersion()

fun generateVersion(): String {
    val raylibVersion = libs.versions.raylib.get()
    val now = LocalDateTime.now()
    val versionBase = now.format(DateTimeFormatter.ofPattern("YYYY.MM.dd"))
    val version = "${raylibVersion}-$versionBase"
    return version
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ffm"))
    testImplementation(kotlin("test"))
}

application {
    val raylibLibrary = File("${projectDir}\\libs")
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