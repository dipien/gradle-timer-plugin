plugins {
    id("com.gradle.enterprise").version("3.6.3")
}

include(":gradle-timer-plugin")

apply(from = java.io.File(settingsDir, "buildCacheSettings.gradle"))
