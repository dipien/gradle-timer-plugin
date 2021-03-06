package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.propertyResolver
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleTimerPlugin : Plugin<Project> {

    companion object {
        const val EXTENSION_NAME = "jdroidGradleTimer"
    }

    override fun apply(project: Project) {
        val extension = project.extensions.create(EXTENSION_NAME, GradleTimerExtension::class.java, project.propertyResolver)
        project.afterEvaluate {
            project.gradle.addListener(TimingsListener(extension))
        }
    }
}
