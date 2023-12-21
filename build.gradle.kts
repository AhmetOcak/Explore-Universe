buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        // AppGallery Connect plugin configuration
        classpath("com.huawei.agconnect:agcp:1.7.1.300")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}