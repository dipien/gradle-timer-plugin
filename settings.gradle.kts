plugins {
    id("com.gradle.enterprise").version("3.3.4")
}

include(":gradle-timer-plugin")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))
