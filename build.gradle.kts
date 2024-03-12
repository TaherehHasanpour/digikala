buildscript {
    System.setProperty("retrofit_version", "2.9.0")
    System.setProperty("gson_version", "2.10.1")
    System.setProperty("okhttpInterceptor_version", "4.11.0")
    System.setProperty("roomDB_version", "2.6.1")
    System.setProperty("datastore_version", "1.0.0")
    System.setProperty("hilt_version", "2.44")
    System.setProperty("nav_version", "2.7.5")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version System.getProperty("hilt_version") apply false
}
