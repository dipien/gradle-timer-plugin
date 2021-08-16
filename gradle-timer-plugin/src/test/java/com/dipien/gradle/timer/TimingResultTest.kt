package com.dipien.gradle.timer

import org.gradle.StartParameter
import org.junit.Assert
import org.junit.Test
import java.util.Date

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
    fun androidLocationTest() {
        val timingResult = createTimingResult(listOf("clean"), mapOf("android.injected.attribution.file.location" to "/Users/user1/build"))
        Assert.assertEquals("clean", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun androidLocation2Test() {
        val timingResult = createTimingResult(listOf("clean"), mapOf("android.injected.attribution.file.location" to "/Users/user1/build"))
        timingResult.startParameter.isBuildCacheEnabled = true
        Assert.assertEquals("clean --build-cache", timingResult.getSimplifiedExecutedTasks())
    }

    @Test
    fun projectPropertiesTest() {
        val timingResult = createTimingResult(listOf("clean"))
        timingResult.startParameter.projectProperties = mapOf("param" to "value")
        Assert.assertEquals("clean -Pparam=value", timingResult.getSimplifiedExecutedTasks())
    }

    private fun createTimingResult(taskNames: List<String>, projectProperties: Map<String, String> = emptyMap()): TimingResult {
        val startParameter = StartParameter()
        startParameter.setTaskNames(taskNames)
        startParameter.projectProperties = projectProperties
        return TimingResult("test", 0, startParameter, Date(), GradleTimerExtension(FakePropertyResolver()))
    }
}
