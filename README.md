# Gradle Timer Plugin
Gradle Plugin to measure the build time of a project

## Setup 

Add the following configuration to your `build.gradle`:

    apply plugin: 'com.jdroid.gradle.timer'

    buildscript {
      repositories {
        mavenCentral()
      }
      dependencies {
        classpath 'com.jdroidframework:jdroid-gradle-timer-plugin:0.9.0'
      }
    }
    
    jdroidGradleTimer {
      enableProfiling = true
      profilingTag = 'TAG_NAME'
      url = 'URL_TO_POST_RESULTS'
    }

-----------
Help us to continue with this project:

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=2UEBTRTSCYA9L)
