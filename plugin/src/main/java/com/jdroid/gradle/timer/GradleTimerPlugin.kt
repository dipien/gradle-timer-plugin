package com.jdroid.gradle.timer

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleTimerPlugin : Plugin<Project> {

    companion object {
        const val EXTENSION_NAME = "jdroidGradleTimer"
    }

    override fun apply(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, GradleTimerExtension::class.java, this)
        project.afterEvaluate {
            project.gradle.addListener(TimingsListener(extension.profilingTag, extension.enableLogs))
        }
    }
}