package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.AbstractBuildListener
import com.jdroid.java.date.DateUtils
import org.gradle.BuildResult
import org.gradle.api.invocation.Gradle

class TimingsListener(private val tag: String, private val buildHook: BuildHook?) : AbstractBuildListener() {

    private var timestamp: Long? = null
    private var executedTasks: String? = null

    val isValidExecutedTasks: Boolean
        get() = executedTasks != null && executedTasks != "[]" && executedTasks != "[ ]"

    override fun buildFinished(result: BuildResult) {
        if (isValidExecutedTasks) {
            val now = DateUtils.now()
            val time = now.time - timestamp!!
            val timingResult = TimingResult(tag, time, executedTasks!!, now)
            if (result.failure == null) {
                buildHook?.onBuildSuccess(timingResult)
            } else {
                buildHook?.onBuildFail(timingResult, result.failure!!)
            }
        }
    }

    override fun projectsEvaluated(gradle: Gradle) {
        executedTasks = gradle.startParameter.taskNames.toString()
        timestamp = System.currentTimeMillis()
    }
}
