// Top-level build.gradle
buildscript {
    repositories {
        google()                // مخزن اصلی گوگل
        mavenCentral()          // مخزن اصلی Maven Central
        gradlePluginPortal()    // برای پلاگین‌های Gradle
    }
    dependencies {
        // اگر نیاز به کلاس‌پث برای buildscript دارید (مثلا پلاگین خاص)، اینجا اضافه کنید
        // معمولا خالی است چون پلاگین‌ها از طریق plugins{} معرفی می‌شوند
    }
}

plugins {
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false   // نسخه ثابت (بدون System.getProperty)
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}



//buildscript {
////    System.setProperty("retrofit_version", "2.9.0")
////    System.setProperty("gson_version", "2.10.1")
////    System.setProperty("roomDB_version", "2.6.0")
////    System.setProperty("datastore_version", "1.0.0")
////    System.setProperty("hilt_version", "2.48")
//    repositories {
//        google()
//    }
//
//}
//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "8.3.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
//    id("com.google.dagger.hilt.android") version System.getProperty("hilt_version") apply false
//    id ("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
//
//
//}
