# Gradle Timer Plugin
Gradle Plugin to measure the build times of a project

## Continuous Integration
|Branch|Status|Workflows|Insights|
| ------------- | ------------- | ------------- | ------------- |
|master|[![CircleCI](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/master.svg?style=svg)](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/master)|[Workflows](https://circleci.com/gh/maxirosson/workflows/jdroid-gradle-timer-plugin/tree/master)|[Insights](https://circleci.com/build-insights/gh/maxirosson/jdroid-gradle-timer-plugin/master)|
|production|[![CircleCI](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/production.svg?style=svg)](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/production)|[Workflows](https://circleci.com/gh/maxirosson/workflows/jdroid-gradle-timer-plugin/tree/production)|[Insights](https://circleci.com/build-insights/gh/maxirosson/jdroid-gradle-timer-plugin/production)|

## Setup

Add the following configuration to your root `build.gradle`, replacing X.Y.Z by the [latest version](https://github.com/maxirosson/jdroid-gradle-timer-plugin/releases/latest)

Using the plugins DSL:

```groovy
plugins {
  id "com.jdroidtools.gradle.timer" version "X.Y.Z"
}
```

Using legacy plugin application:

```groovy
buildscript {
    repositories {
        mavenCentral() // or gradlePluginPortal()
    }
    dependencies {
        classpath("com.jdroidtools:jdroid-gradle-timer-plugin:X.Y.Z")
    }
}
    
apply plugin: "com.jdroidtools.gradle.timer"

jdroidGradleTimer {
  // A tag that will be added to each report
  tag = 'TAG_NAME'
}
```

## Usage

Every time a build is executed on gradle, this plugin will invoke the buildHook with timing info. For example: 
    
    tag: "test"
    timing: 107
    executedTasks: "[clean]"
    date: "2015-08-22 21:24:49"

## Donations
Help us to continue with this project:

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=2UEBTRTSCYA9L)
