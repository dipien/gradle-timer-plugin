plugins {
    id("com.gradle.enterprise").version("3.7.2")
}

include(":gradle-timer-plugin")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))
