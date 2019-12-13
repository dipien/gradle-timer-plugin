package com.jdroid.gradle.timer

import org.gradle.api.Project

open class GradleTimerExtension(project: Project) {
    var enableProfiling: Boolean = false
    var profilingTag: String? = null
    var url: String? = null
    var enableLogs = false
}