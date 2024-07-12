// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.org.jetbrains.kotlin.parcelize) apply false
}
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}