package com.dipien.gradle.timer.common

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

abstract class AbstractBuildListener : BuildListener {
    override fun settingsEvaluated(settings: Settings) {}

    override fun buildFinished(result: BuildResult) {}

    override fun projectsLoaded(gradle: Gradle) {}

    override fun projectsEvaluated(gradle: Gradle) {}
}
