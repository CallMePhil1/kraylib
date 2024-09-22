rootProject.name = "kraylib"

val raylibVersion = "5.0"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "2.0.10")
            version("raylib", "5.0")

            plugin("kotlin", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
        }
    }
}

include("core", "ffm", "ui")
