package com.jdroid.gradle.timer.common

import org.gradle.api.Project

private val propertyResolverCache = mutableMapOf<Project, PropertyResolver>()

val Project.propertyResolver: PropertyResolver
    get() = propertyResolverCache.getOrPut(this) { PropertyResolverImpl(this) }
