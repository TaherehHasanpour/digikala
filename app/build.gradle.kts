import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

val apiKeyPropertiesFile = rootProject.file("key.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(apiKeyPropertiesFile.inputStream())

android {
    namespace = "com.example.digikala"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.digikala"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "X_API_KEY", apiKeyProperties.getProperty("X_API_KEY"))
        buildConfigField("String", "KEY", apiKeyProperties.getProperty("KEY"))
        buildConfigField("String", "IV", apiKeyProperties.getProperty("IV"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material:1.6.8")
    implementation("androidx.compose.ui:ui-text-android:1.6.8")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${System.getProperty("retrofit_version")}")
    implementation("com.squareup.retrofit2:converter-gson:${System.getProperty("retrofit_version")}")

    //gson
    implementation("com.google.code.gson:gson:${System.getProperty("gson_version")}")

    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:${System.getProperty("okhttpInterceptor_version")}")

    //roomDB
    implementation("androidx.room:room-runtime:${System.getProperty("roomDB_version")}")
    implementation("androidx.room:room-ktx:${System.getProperty("roomDB_version")}")
    annotationProcessor("androidx.room:room-compiler:${System.getProperty("roomDB_version")}")
    kapt("androidx.room:room-compiler:${System.getProperty("roomDB_version")}")

    //datastore
    implementation("androidx.datastore:datastore-preferences:${System.getProperty("datastore_version")}")

    //hilt
    implementation("com.google.dagger:hilt-android:${System.getProperty("hilt_version")}")
    kapt("com.google.dagger:hilt-android-compiler:${System.getProperty("hilt_version")}")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //compose navigation
    implementation("androidx.navigation:navigation-compose:${System.getProperty("nav_version")}")

    //animation
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    //coil - load image from url
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    //Accompanist-Pager
    implementation("com.google.accompanist:accompanist-pager:0.29.0-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.29.0-alpha")

    //swipe refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.27.0")

    //system ui controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.0")

    //Accompanist-Pager
    implementation("com.google.accompanist:accompanist-pager:0.29.0-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.29.0-alpha")

    //zarinpal
    implementation("com.zarinpal:payment-provider-ktx:0.5.3")

    //paging
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")


}

kapt {
    correctErrorTypes = true
}