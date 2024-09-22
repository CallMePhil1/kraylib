plugins {
    alias(libs.plugins.kotlin)
}

group = "com.github.callmephil1"
version = libs.versions.raylib.get()

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("generateRaylib") {
    val headerFile = ".\\raylib\\raylib.h"
    val library = "raylib"
    val outputDir = "src\\main\\java"
    val packageName = "kraylib.ffm"

    val argsString = "jextract.bat --output $outputDir --target-package $packageName --library $library $headerFile"
    val args = argsString.split(" ")

    val proc = ProcessBuilder(args)
        .directory(projectDir)
        .redirectInput(ProcessBuilder.Redirect.PIPE)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    proc.waitFor(15, TimeUnit.SECONDS)

    val fullPath = "$projectDir\\$outputDir\\${packageName.replace('.','\\')}"
    val baseDirectory = File(fullPath)
    println("Full path: $fullPath")

    baseDirectory.listFiles()?.forEach {
        val fileContents = it.readText()
        val newContents = fileContents.replace("raylib_h", "Raylib")
        it.writeText(newContents)
    }

    val raylibFile = File(fullPath, "raylib_h.java")
    raylibFile.renameTo(File(fullPath, "Raylib.java"))
    File(fullPath, "raylib_h.java").delete()
}