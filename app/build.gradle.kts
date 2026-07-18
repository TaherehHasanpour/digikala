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
        kotlinCompilerExtensionVersion = "1.5.2"
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

    //retrofit - نسخه‌ها را مستقیم بنویسید
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //gson - حذف خط تکراری
    implementation("com.google.code.gson:gson:2.10.1")

    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //roomDB
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")  // فقط kapt کافیست، annotationProcessor را حذف کنید

    //datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //compose navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //animation
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    //coil - load image from url
    implementation("io.coil-kt:coil-compose:2.6.0")

    //Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.32.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.32.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.32.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    //zarinpal
    implementation("com.zarinpal:payment-provider-ktx:0.5.3")

    //paging
    implementation("androidx.paging:paging-compose:3.3.0-rc01")

    //chart
    implementation("com.patrykandpatrick.vico:compose:1.15.0")
}

kapt {
    correctErrorTypes = true
}