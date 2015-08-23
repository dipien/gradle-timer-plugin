# Gradle Timer Plugin
Gradle Plugin to measure the build times of a project

Every time a build is executed on gradle, this plugin will report the build time to a server. You can use [Quick Entities](https://github.com/fernandospr/quick-entities) to host the server.

The following is an example of the POST request that the plugin will execute:

    {
        id: "-248736759798762853",
        timing: 107,
        executedTasks: "[clean]",
        cpu: "Intel(R) Core(TM) i5-3427U CPU @ 1.80GHz",
        os: "Mac OS X 10.10.4 x86_64",
        tag: "test",
        date: "2015-08-22 21:24:49"
    }

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

<a href='https://pledgie.com/campaigns/30030'><img alt='Click here to lend your support to: Jdroid and make a donation at pledgie.com !' src='https://pledgie.com/campaigns/30030.png?skin_name=chrome' border='0' ></a>
