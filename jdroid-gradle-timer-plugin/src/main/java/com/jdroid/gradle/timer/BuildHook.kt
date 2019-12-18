package com.jdroid.gradle.timer

interface BuildHook {

    fun onBuildSuccess(timingResult: TimingResult)

    fun onBuildFail(timingResult: TimingResult, failure: Throwable)
}
