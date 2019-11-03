/**
 *
 */

object Version
{
    const val kotlin = "1.3.50"
    const val detekt = "1.0.0-RC11"
}

plugins {

    kotlin("gradle-plugin") version Version.kotlin

    id("io.gitlab.arturbosch.detekt") version Version.detekt
}

buildscript {

    repositories {
        mavenCentral()
        jcenter()

        maven("https://plugins.gradle.org/m2/")
    }
}

group = "info.digitalpoet.eengine"
version = "0.0.1"

subprojects {

    group = rootProject.group
    version = rootProject.version

    dependencies {
        "implementation"("")
    }
}
