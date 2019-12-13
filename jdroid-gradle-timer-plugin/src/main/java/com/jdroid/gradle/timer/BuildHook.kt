package com.jdroid.gradle.timer

interface BuildHook {

    fun onBuildSuccess(result: TimingResult)

    fun onBuildFail(result: TimingResult, failure: Throwable)
}
