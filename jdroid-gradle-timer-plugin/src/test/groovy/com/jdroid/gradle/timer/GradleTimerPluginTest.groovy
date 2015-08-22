package com.jdroid.gradle.timer

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

public class GradleTimerPluginTest {

	@Test
	public void pluginTest() {
		Project project = ProjectBuilder.builder().build()
		project.apply plugin: 'com.jdroid.gradle.timer'
	}

}
