package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.AbstractBuildListener
import com.jdroid.java.date.DateUtils
import org.gradle.BuildResult
import org.gradle.api.invocation.Gradle

class TimingsListener(private val tag: String, private val buildHook: BuildHook?) : AbstractBuildListener() {

    private var timestamp: Long? = null

    override fun buildFinished(result: BuildResult) {
        if (!result.gradle!!.startParameter.taskNames.isNullOrEmpty()) {
            val now = DateUtils.now()
            val time = if (timestamp != null) now.time - timestamp!! else null
            val timingResult = TimingResult(tag, time, result.gradle!!.startParameter, now)
            if (result.failure == null) {
                buildHook?.onBuildSuccess(timingResult)
            } else {
                buildHook?.onBuildFail(timingResult, result.failure!!)
            }
        }
    }

    override fun projectsEvaluated(gradle: Gradle) {
        timestamp = System.currentTimeMillis()
    }
}
