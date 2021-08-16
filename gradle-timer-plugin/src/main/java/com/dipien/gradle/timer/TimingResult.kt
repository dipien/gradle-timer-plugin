package com.dipien.gradle.timer

import org.gradle.StartParameter
import java.util.Date

data class TimingResult(
    var tag: String,
    var timing: Long?,
    var startParameter: StartParameter,
    var date: Date,
    private val extension: GradleTimerExtension
) {
    fun getSimplifiedExecutedTasks(): String {
        val tasksList = mutableListOf<String>()

        // key: task name, value: module
        val modulesByTaskMap = mutableMapOf<String, String>()
        startParameter.taskNames.forEach { task ->
            if (task.count { char -> char == ':' } == 2) {
                val split = task.split(":")
                val taskName = split.last()
                if (modulesByTaskMap[taskName] == null) {
                    modulesByTaskMap[taskName] = split[1]
                } else {
                    modulesByTaskMap[taskName] = "*"
                }
            }
        }

        startParameter.taskNames.forEach {
            if (it.count { char -> char == ':' } == 2) {
                val task = it.split(":").last()
                val module = modulesByTaskMap[task]
                tasksList.add(":$module:$task")
            } else {
                tasksList.add(it)
            }
        }

        val builder = StringBuilder()
        tasksList.distinct().forEach {
            builder.append(" $it")
        }

        if (!startParameter.projectProperties.isNullOrEmpty()) {
            startParameter.projectProperties.forEach { (k, v) ->
                if (!extension.projectPropertiesToIgnore.contains(k)) {
                    builder.append(" -P$k=$v")
                }
            }
        }

        if (startParameter.isBuildCacheEnabled) {
            builder.append(" --build-cache")
        }

        return builder.toString().trim()
    }
}
