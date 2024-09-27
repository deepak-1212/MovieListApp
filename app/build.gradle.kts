plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "deepak.developer.movie_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "deepak.developer.movie_app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "MOVIE_BASE_URL", "\"https://www.omdbapi.com\"")
        buildConfigField("String", "MOVIE_API_KEY", "\"5b24b5aa\"")
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
    dataBinding {
        enable = true
    }
    buildFeatures {
        buildConfig = true
    }


}

dependencies {

    api(libs.androidx.core.ktx)
    (libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.activity)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.databinding.common)
    api(libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.androidx.paging)
    api(libs.bumptech.glide)
    ksp(libs.bumptech.glide.compiler)
    ksp(libs.hilt.android.compiler)
    api(libs.hilt.android)
}

tasks.register("printVersionName") {
    println("v" + android.defaultConfig.versionName + "(" + android.defaultConfig.versionCode + ")")
}
