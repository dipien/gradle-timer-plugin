package com.jdroid.gradle.timer

import org.gradle.StartParameter
import java.util.Date

data class TimingResult(
    var tag: String,
    var timing: Long?,
    var startParameter: StartParameter,
    var date: Date
) {
    fun getSimplifiedExecutedTasks(): String {
        val tasksList = mutableListOf<String>()
        startParameter.taskNames.forEach {
            if (it.startsWith(":")) {
                tasksList.add(it.split(":").last())
            } else {
                tasksList.add(it)
            }
        }

        return tasksList.distinct().toString().replace(",", " ").replace("[", "").replace("]", "")
    }
}
