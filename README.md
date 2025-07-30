[![Dipien](https://raw.githubusercontent.com/dipien/dipien-component-builder/master/.github/dipien_logo.png)]([http://www.dipien.com](https://medium.com/dipien))

# Gradle Timer Plugin
Gradle Plugin to measure the build times of a project

## Setup

Add the following configuration to your root `build.gradle`, replacing X.Y.Z by the [latest version](https://github.com/dipien/gradle-timer-plugin/releases/latest)

Using the plugins DSL:

```groovy
plugins {
  id "com.dipien.gradle.timer" version "X.Y.Z"
}
```

Using legacy plugin application:

```groovy
buildscript {
    repositories {
        mavenCentral() // or gradlePluginPortal()
    }
    dependencies {
        classpath("com.dipien:gradle-timer-plugin:X.Y.Z")
    }
}
    
apply plugin: "com.dipien.gradle.timer"

gradleTimer {
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
