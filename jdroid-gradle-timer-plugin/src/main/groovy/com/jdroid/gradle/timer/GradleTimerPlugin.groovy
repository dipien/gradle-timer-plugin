package com.jdroid.gradle.timer

import org.gradle.api.Plugin
import org.gradle.api.Project

public class GradleTimerPlugin implements Plugin<Project> {

	@Override
	void apply(Project project) {
		project.extensions.create("jdroidGradleTimer", GradleTimerExtension.class, this)
		project.afterEvaluate {
			if (project.jdroidGradleTimer.enableProfiling) {
				project.gradle.addListener new TimingsListener(project.jdroidGradleTimer.profilingTag, project.jdroidGradleTimer.url)
			}
		}
	}
}
