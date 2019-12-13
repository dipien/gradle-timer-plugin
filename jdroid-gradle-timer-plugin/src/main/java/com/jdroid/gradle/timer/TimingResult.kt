package com.jdroid.gradle.timer

import java.util.Date

data class TimingResult(
    var tag: String,
    var buildTime: Long,
    var executedTasks: String,
    var date: Date
)
