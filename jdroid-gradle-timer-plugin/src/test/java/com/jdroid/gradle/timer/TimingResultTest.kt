package com.jdroid.gradle.timer

import com.jdroid.java.date.DateUtils
import org.gradle.StartParameter
import org.junit.Assert
import org.junit.Test

class TimingResultTest {

    @Test
    fun getSimplifiedExecutedTasksTest() {
        val timingResult = createTimingResult(listOf("clean"))
        Assert.assertEquals("clean", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun getSimplifiedExecutedTasksTest2() {
        val timingResult = createTimingResult(listOf("clean", "assemble"))
        Assert.assertEquals("clean assemble", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun getSimplifiedExecutedTasksTest3() {
        val timingResult = createTimingResult(listOf("clean", ":app:assemble"))
        Assert.assertEquals("clean :app:assemble", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun getSimplifiedExecutedTasksTest4() {
        val timingResult = createTimingResult(listOf("clean", ":app:assemble", ":module2:assemble"))
        Assert.assertEquals("clean :*:assemble", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun getSimplifiedExecutedTasksTest5() {
        val timingResult = createTimingResult(listOf("clean", ":test", ":app:assemble", ":module2:assemble"))
        Assert.assertEquals("clean :test :*:assemble", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun buildCacheTest() {
        val timingResult = createTimingResult(listOf("clean"))
        timingResult.startParameter.isBuildCacheEnabled = true
        Assert.assertEquals("clean --build-cache", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun projectPropertiesTest() {
        val timingResult = createTimingResult(listOf("clean"))
        timingResult.startParameter.projectProperties = mapOf("param" to "value")
        Assert.assertEquals("clean -Pparam=value", timingResult.getSimplifiedExecutedTasks())
    }

    private fun createTimingResult(taskNames: List<String>): TimingResult {
        val startParameter = StartParameter()
        startParameter.setTaskNames(taskNames)
        return TimingResult("test", 0, startParameter, DateUtils.now())
    }
}
