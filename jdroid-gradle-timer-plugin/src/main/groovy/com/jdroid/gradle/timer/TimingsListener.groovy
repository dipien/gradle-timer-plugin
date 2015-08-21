package com.jdroid.gradle.timer

import com.jdroid.java.http.DefaultServer
import com.jdroid.java.http.okhttp.post.OkPostHttpService
import com.jdroid.java.http.post.BodyEnclosingHttpService
import com.jdroid.java.utils.DateUtils
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import org.gradle.util.Clock

public class TimingsListener implements TaskExecutionListener, BuildListener {

	private Clock clock = null
	private String profilingTag;
	private String url;

	public TimingsListener(String profilingTag, String url) {
		this.profilingTag = profilingTag;
		this.url = url;
	}

	@Override
	void buildStarted(Gradle gradle) { }

	@Override
	void buildFinished(BuildResult result) {
		if (clock != null && result.getFailure() == null) {
			def time = clock.timeInMs
			String cpu = SysInfo.getCPUIdentifier()
			String os = SysInfo.getOSIdentifier()

			try {
				BodyEnclosingHttpService service = new OkPostHttpService(new DefaultServer(url), null, null);

				StringBuilder builder = new StringBuilder();
				builder.append("{\"id\":");
				builder.append(new Random().nextLong());
				builder.append(",\"timing\":");
				builder.append(time);
				builder.append(",\"cpu\":\"");
				builder.append(cpu);
				builder.append("\",\"os\":\"");
				builder.append(os);
				builder.append("\",\"tag\":\"");
				builder.append(profilingTag);
				builder.append("\",\"date\":\"");
				builder.append(DateUtils.format(DateUtils.now(), DateUtils.YYYYMMDDHHMMSS_DATE_FORMAT));
				builder.append("\"}");

				service.setBody(builder.toString());
				service.execute();
			} catch (Exception e) {
				println e.getMessage()
			}
		}
	}

	@Override
	void beforeExecute(Task task) { }

	@Override
	void afterExecute(Task task, TaskState taskState) { }

	@Override
	void projectsEvaluated(Gradle gradle) {
		if (clock == null) {
			clock = new org.gradle.util.Clock()
		}
	}

	@Override
	void projectsLoaded(Gradle gradle) { }

	@Override
	void settingsEvaluated(Settings settings) { }
}