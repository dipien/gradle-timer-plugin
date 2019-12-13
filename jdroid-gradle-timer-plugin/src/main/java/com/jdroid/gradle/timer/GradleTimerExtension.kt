package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.PropertyResolver
import org.gradle.api.Project

open class GradleTimerExtension(project: Project) {

    private val propertyResolver: PropertyResolver = PropertyResolver(project)

    var profilingTag: String? = propertyResolver.getStringProp(::profilingTag.name)
    var enableLogs: Boolean = propertyResolver.getBooleanProp(::enableLogs.name, false) ?: false
    var buildHook: BuildHook? = null
}
