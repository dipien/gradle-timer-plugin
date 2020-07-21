plugins {
    id("com.gradle.enterprise").version("3.3.4")
}

include(":jdroid-gradle-timer-plugin")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))
