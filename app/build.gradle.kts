/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.20-1.0.14"
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kamteamapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.example.kamteamapp"
}

dependencies {
    //json
    implementation ("com.google.code.gson:gson:2.8.6")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0") //
    // Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    //implementation(libs.androidx.material3.android)
    implementation("androidx.datastore:datastore:1.0.0")
    implementation ("com.google.dagger:hilt-android:2.51")
    //Room
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.lifecycle:lifecycle-process:2.8.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:3.0.2")
    implementation("androidx.databinding:compiler:3.2.0-alpha11")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    ksp("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
    implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")
    // Import the Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Gson 库，用于解析 JSON（可选，但推荐）
    implementation ("com.google.code.gson:gson:2.8.8")

    // OkHttp 核心库
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.14")
    // OkHttp 日志拦截器
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14")

    //implementation 'androidx.navigation:navigation-fragment-ktx:2.4.1'
    //implementation 'androidx.navigation:navigation-ui-ktx:2.4.1'

    // Testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    //json
    implementation ("com.google.code.gson:gson:2.8.6")
    //Retrofit
    implementation("com.google.guava:guava:30.1-android") {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }
}
