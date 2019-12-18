package com.jdroid.gradle.timer

import com.jdroid.gradle.timer.common.AbstractBuildListener
import com.jdroid.java.date.DateUtils
import org.gradle.BuildResult
import org.gradle.StartParameter
import org.gradle.api.invocation.Gradle

class TimingsListener(private val tag: String, private val buildHook: BuildHook?) : AbstractBuildListener() {

    private var timestamp: Long? = null

    override fun buildFinished(result: BuildResult) {
        if (!ignoreTracking(result.gradle!!.startParameter)) {
            val now = DateUtils.now()
            val timing = if (timestamp != null) now.time - timestamp!! else null
            val timingResult = TimingResult(tag, timing, result.gradle!!.startParameter, now)
            if (result.failure == null) {
                buildHook?.onBuildSuccess(timingResult)
            } else {
                buildHook?.onBuildFail(timingResult, result.failure!!)
            }
        }
    }

    private fun ignoreTracking(startParameter: StartParameter): Boolean {
        return startParameter.taskNames.isNullOrEmpty() || startParameter.isRefreshDependencies || startParameter.isBuildScan || startParameter.isProfile
    }

    override fun projectsEvaluated(gradle: Gradle) {
        timestamp = System.currentTimeMillis()
    }
}
