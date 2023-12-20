import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.huawei.agconnect")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.spaceapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "NASA_API_KEY", "\"${properties.getProperty("NASA_API_KEY")}\"")
        buildConfigField("String", "SPACE_NEWS_API_KEY", "\"${properties.getProperty("SPACE_NEWS_API_KEY")}\"")
        buildConfigField("String", "WEATHER_API_KEY", "\"${properties.getProperty("WEATHER_API_KEY")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging  {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.spaceapp"
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Local Unit Tests
    implementation("androidx.test:core:1.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.robolectric:robolectric:4.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    testImplementation("com.google.truth:truth:1.1.3")

    // Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")

    // Coil
    implementation("io.coil-kt:coil:2.1.0")
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("io.coil-kt:coil-gif:2.1.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.3.0")

    // Okhttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    
    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // HMS
    implementation("com.huawei.agconnect:agconnect-core:1.7.2.300")
    // HMS Auth Service
    implementation("com.huawei.agconnect:agconnect-auth:1.7.2.300")
    // HMS Location
    implementation("com.huawei.hms:location:6.7.0.300")

    // Firebase
    implementation(platform(("com.google.firebase:firebase-bom:31.0.2")))
    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")
    // GMS Location
    implementation("com.google.android.gms:play-services-location:21.0.1")
}