[![Dipien](https://raw.githubusercontent.com/dipien/dipien-component-builder/master/.github/dipien_logo.png)](http://www.dipien.com)

# Gradle Timer Plugin
Gradle Plugin to measure the build times of a project

## Continuous Integration
|Branch|Status|Workflows|Insights|
| ------------- | ------------- | ------------- | ------------- |
|master|[![CircleCI](https://circleci.com/gh/dipien/gradle-timer-plugin/tree/master.svg?style=svg)](https://circleci.com/gh/dipien/gradle-timer-plugin/tree/master)|[Workflows](https://circleci.com/gh/dipien/workflows/gradle-timer-plugin/tree/master)|[Insights](https://circleci.com/build-insights/gh/dipien/gradle-timer-plugin/master)|
|production|[![CircleCI](https://circleci.com/gh/dipien/gradle-timer-plugin/tree/production.svg?style=svg)](https://circleci.com/gh/dipien/gradle-timer-plugin/tree/production)|[Workflows](https://circleci.com/gh/dipien/workflows/gradle-timer-plugin/tree/production)|[Insights](https://circleci.com/build-insights/gh/dipien/gradle-timer-plugin/production)|

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

## Donations

Donations are greatly appreciated. You can help us to pay for our domain and this project development.

* [Donate cryptocurrency](http://coinbase.dipien.com/)
* [Donate with PayPal](http://paypal.dipien.com/)
* [Donate on Patreon](http://patreon.dipien.com/)

## Follow us
* [Twitter](https://twitter.com/ReleasesHub)
* [Medium](http://medium.dipien.com)
* [Blog](http://blog.dipien.com)
