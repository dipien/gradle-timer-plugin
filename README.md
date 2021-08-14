[![Dipien](https://raw.githubusercontent.com/dipien/dipien-component-builder/master/.github/dipien_logo.png)](http://www.dipien.com)

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

## Sponsor this project

Sponsor this open source project to help us get the funding we need to continue working on it.

* [Donate cryptocurrency](http://coinbase.dipien.com/)
* [Donate with PayPal](http://paypal.dipien.com/)
* [Donate on Patreon](http://patreon.dipien.com/)
* [Become a member of Medium](https://maxirosson.medium.com/membership) [We will receive a portion of your membership fee]

## Follow us
* [Twitter](http://twitter.dipien.com)
* [Medium](http://medium.dipien.com)
* [Instagram](http://instagram.dipien.com)
* [Pinterest](http://pinterest.dipien.com)
* [GitHub](http://github.dipien.com)
