package com.jdroid.gradle.timer

import com.jdroid.java.date.DateUtils
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TimingsListener(
    private val profilingTag: String?,
    private val enableLogs: Boolean
) : TaskExecutionListener, BuildListener {

    private var timestamp: Long? = null
    private var executedTasks: String? = null

    val isValidExecutedTasks: Boolean
        get() = executedTasks != null && executedTasks != "[]" && executedTasks != "[ ]"

    override fun buildStarted(gradle: Gradle) {}

    override fun buildFinished(result: BuildResult) {
        if (result.failure == null && isValidExecutedTasks) {
            val time = System.currentTimeMillis() - timestamp!!
            try {
                val timingResult = TimingResult(profilingTag!!, time, executedTasks!!, DateUtils.now())
                if (enableLogs) {
                    println(timingResult.toString())
                }
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

}
