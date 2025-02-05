plugins {
    alias(libs.plugins.android.library)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "uz.gita.presenter"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {
    implementation(project(":common"))
    implementation(project(":usecase"))

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    implementation(libs.voyager.screenModel)
    implementation(libs.voyager.hilt)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation("org.orbit-mvi:orbit-core:9.0.0")
    implementation("org.orbit-mvi:orbit-viewmodel:9.0.0")



    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("com.airbnb.android:lottie-compose:6.6.2")
    val paging_version = "3.3.5"

    implementation("androidx.paging:paging-runtime-ktx:$paging_version")
}