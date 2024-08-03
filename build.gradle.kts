buildscript {
    System.setProperty("retrofit_version", "2.9.0")
    System.setProperty("gson_version", "2.10.1")
    System.setProperty("roomDB_version", "2.6.0")
    System.setProperty("datastore_version", "1.0.0")
    System.setProperty("hilt_version", "2.48")
    repositories {
        google()
    }

}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version System.getProperty("hilt_version") apply false
    id ("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false


}
