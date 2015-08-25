package com.jdroid.gradle.timer

public class GradleTimerExtension {

	private final GradleTimerPlugin gradleTimerPlugin

	Boolean enableProfiling = false
	String profilingTag
	String url
	Boolean enableLogs = false

	public GradleTimerExtension(GradleTimerPlugin gradleTimerPlugin) {
		this.gradleTimerPlugin = gradleTimerPlugin
	}
}
