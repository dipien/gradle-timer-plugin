package com.jdroid.gradle.timer

import com.jdroid.java.date.DateTimeFormat
import com.jdroid.java.date.DateUtils
import com.jdroid.java.date.DateUtils.format
import com.jdroid.java.date.DateUtils.now
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TimingsListener(
    private val profilingTag: String,
    private val url: String,
    private val enableLogs: Boolean
) : TaskExecutionListener, BuildListener {

    val isValidExecutedTasks: Boolean
        get() = executedTasks != null && executedTasks != "[]" && executedTasks != "[ ]"

    override fun buildStarted(gradle: Gradle) {}

    override fun buildFinished(result: BuildResult) {
        if (result.failure == null && isValidExecutedTasks) {
            val time = System.currentTimeMillis() - timestamp!!
            val cpu = SysInfo.getCPUIdentifier()
            val os = SysInfo.getOSIdentifier()
            try {
                val builder = StringBuilder()
                builder.append("{\"timing\":")
                builder.append(time)
                builder.append(",\"executedTasks\":\"")
                builder.append(executedTasks)
                builder.append("\",\"cpu\":\"")
                builder.append(cpu)
                builder.append("\",\"os\":\"")
                builder.append(os)
                builder.append("\",\"tag\":\"")
                builder.append(profilingTag)
                builder.append("\",\"date\":\"")
                builder.append(DateUtils.format(now(), DateTimeFormat.YYYYMMDDHHMMSS))
                builder.append("\"}")
                if (enableLogs) {
                    println("Url: " + service.url)
                    println("Body: $builder")
                }
                service.execute()
            } catch (e: Exception) {
                if (enableLogs) {
                    println(e.message)
                }
            }
        }
    }

    override fun beforeExecute(task: Task) {}
    override fun afterExecute(task: Task, taskState: TaskState) {}
    override fun projectsEvaluated(gradle: Gradle) {
        executedTasks = gradle.startParameter.taskNames.toString()
        timestamp = System.currentTimeMillis()
    }

    override fun projectsLoaded(gradle: Gradle) {}
    override fun settingsEvaluated(settings: Settings) {}
    private var timestamp: Long? = null
    private var executedTasks: String? = null
}
