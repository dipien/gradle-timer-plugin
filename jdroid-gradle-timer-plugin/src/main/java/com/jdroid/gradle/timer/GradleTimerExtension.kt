package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.PropertyResolver
import org.gradle.api.Project

open class GradleTimerExtension(project: Project) {

    private val propertyResolver: PropertyResolver = PropertyResolver(project)

    var tag: String = propertyResolver.getRequiredStringProp(::tag.name, "unknown")
    var buildHook: BuildHook? = null
}
