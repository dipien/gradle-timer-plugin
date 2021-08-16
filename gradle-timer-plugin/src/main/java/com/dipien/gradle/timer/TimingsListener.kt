package com.dipien.gradle.timer

import com.dipien.gradle.timer.common.AbstractBuildListener
import org.gradle.BuildResult
import org.gradle.StartParameter
import org.gradle.api.invocation.Gradle
import java.util.Date

class TimingsListener(private val extension: GradleTimerExtension) : AbstractBuildListener() {

    private var timestamp: Long? = null

    override fun buildFinished(result: BuildResult) {
        if (!ignoreTracking(result.gradle!!.startParameter)) {
            val now = Date()
            val timing = if (timestamp != null) now.time - timestamp!! else null
            val timingResult = TimingResult(extension.tag, timing, result.gradle!!.startParameter, now, extension)
            if (result.failure == null) {
                extension.buildHook?.onBuildSuccess(timingResult)
            } else {
                extension.buildHook?.onBuildFail(timingResult, result.failure!!)
            }
        }
    }

    private fun ignoreTracking(startParameter: StartParameter): Boolean {
        return startParameter.taskNames.isNullOrEmpty() || startParameter.isRefreshDependencies || startParameter.isBuildScan || startParameter.isProfile
    }

    override fun projectsEvaluated(gradle: Gradle) {
        timestamp = System.currentTimeMillis()
        extension.buildHook?.onBuildStarted(gradle.startParameter)
    }
}
