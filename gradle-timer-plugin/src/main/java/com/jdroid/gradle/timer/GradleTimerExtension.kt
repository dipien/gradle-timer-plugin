package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.PropertyResolver

open class GradleTimerExtension(propertyResolver: PropertyResolver) {

    var tag: String = propertyResolver.getRequiredStringProp(::tag.name, "unknown")
    var buildHook: BuildHook? = null
    var projectPropertiesToIgnore = listOf("android.injected.attribution.file.location", "org.gradle.kotlin.dsl.provider.cid")
}
