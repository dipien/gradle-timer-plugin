package com.jdroid.gradle.timer

import com.jdroid.java.date.DateTimeFormat
import com.jdroid.java.date.DateUtils
import com.jdroid.java.http.DefaultServer
import com.jdroid.java.http.okhttp.post.OkPostHttpService
import com.jdroid.java.http.post.BodyEnclosingHttpService
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

public class TimingsListener implements TaskExecutionListener, BuildListener {

	private Long timestamp;
	private String profilingTag;
	private String url;
	private String executedTasks;
	private Boolean enableLogs;

	public TimingsListener(String profilingTag, String url, Boolean enableLogs) {
		this.profilingTag = profilingTag;
		this.url = url;
		this.enableLogs = enableLogs;
	}

	Boolean isValidExecutedTasks() {
		return executedTasks != null && !executedTasks.equals("[]") && !executedTasks.equals("[ ]");
	}

	@Override
	void buildStarted(Gradle gradle) { }

	@Override
	void buildFinished(BuildResult result) {
		if (clock != null && result.getFailure() == null && isValidExecutedTasks()) {
			long time = System.currentTimeMillis() - timestamp
			String cpu = SysInfo.getCPUIdentifier()
			String os = SysInfo.getOSIdentifier()

			try {
				BodyEnclosingHttpService service = new OkPostHttpService(new DefaultServer(url), null, null);

				StringBuilder builder = new StringBuilder();
				builder.append("{\"timing\":");
				builder.append(time);
				builder.append(",\"executedTasks\":\"");
				builder.append(executedTasks);
				builder.append("\",\"cpu\":\"");
				builder.append(cpu);
				builder.append("\",\"os\":\"");
				builder.append(os);
				builder.append("\",\"tag\":\"");
				builder.append(profilingTag);
				builder.append("\",\"date\":\"");
				builder.append(DateUtils.format(DateUtils.now(), DateTimeFormat.YYYYMMDDHHMMSS));
				builder.append("\"}");

				service.setBody(builder.toString());

				if (enableLogs) {
					println "Url: " + service.getUrl()
					println "Body: " + builder.toString()
				}

				service.execute();
			} catch (Exception e) {
				if (enableLogs) {
					println e.getMessage()
				}
			}
		}
	}

	@Override
	void beforeExecute(Task task) { }

	@Override
	void afterExecute(Task task, TaskState taskState) { }

	@Override
	void projectsEvaluated(Gradle gradle) {
		executedTasks = gradle.getStartParameter().getTaskNames().toString()
		timestamp = System.currentTimeMillis()
	}

	@Override
	void projectsLoaded(Gradle gradle) { }

	@Override
	void settingsEvaluated(Settings settings) { }
}