# Gradle Timer Plugin
Gradle Plugin to measure the build times of a project

## Continuous Integration
|Branch|Status|Workflows|Insights|
| ------------- | ------------- | ------------- | ------------- |
|master|[![CircleCI](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/master.svg?style=svg)](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/master)|[Workflows](https://circleci.com/gh/maxirosson/workflows/jdroid-gradle-timer-plugin/tree/master)|[Insights](https://circleci.com/build-insights/gh/maxirosson/jdroid-gradle-timer-plugin/master)|
|staging|[![CircleCI](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/staging.svg?style=svg)](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/staging)|[Workflows](https://circleci.com/gh/maxirosson/workflows/jdroid-gradle-timer-plugin/tree/staging)|[Insights](https://circleci.com/build-insights/gh/maxirosson/jdroid-gradle-timer-plugin/staging)|
|production|[![CircleCI](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/production.svg?style=svg)](https://circleci.com/gh/maxirosson/jdroid-gradle-timer-plugin/tree/production)|[Workflows](https://circleci.com/gh/maxirosson/workflows/jdroid-gradle-timer-plugin/tree/production)|[Insights](https://circleci.com/build-insights/gh/maxirosson/jdroid-gradle-timer-plugin/production)|

## Setup 

Add the following configuration to your `build.gradle`, replacing X.Y.Z by the [latest version](https://github.com/maxirosson/jdroid-gradle-timer-plugin/releases/latest)

    apply plugin: 'com.jdroid.gradle.timer'

    buildscript {
      repositories {
        jcenter()
      }
      dependencies {
        classpath 'com.jdroidtools:jdroid-gradle-timer-plugin:X.Y.Z'
      }
    }
    
    jdroidGradleTimer {
    
      // Whether the timing profiling is enabled or not
      enableProfiling = true
      
      // A tag that will be added to each report
      profilingTag = 'TAG_NAME'
      
      // The url (without the http:// scheme) where the results will be posted
      url = 'URL_TO_POST_RESULTS'
      
      // Whether the logs are enabled or not
      enableLogs = false
    }

## Usage

Every time a build is executed on gradle, this plugin will report the build time to a server. You can use [Quick Entities](https://github.com/fernandospr/quick-entities) as the server.

The following is an example of the POST request that the plugin will execute:

    {
        timing: 107,
        executedTasks: "[clean]",
        cpu: "Intel(R) Core(TM) i5-3427U CPU @ 1.80GHz",
        os: "Mac OS X 10.10.4 x86_64",
        tag: "test",
        date: "2015-08-22 21:24:49"
    }

## Donations
Help us to continue with this project:

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=2UEBTRTSCYA9L)
