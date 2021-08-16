package com.dipien.gradle.timer

import org.gradle.StartParameter

interface BuildHook {

    fun onBuildStarted(startParameter: StartParameter) { }

    fun onBuildSuccess(timingResult: TimingResult)

    fun onBuildFail(timingResult: TimingResult, failure: Throwable)
}
