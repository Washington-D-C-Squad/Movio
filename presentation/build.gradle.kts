plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.2.0"
}

android {
    namespace = "com.madrid.presentation"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    android {
        buildFeatures {
            compose = true
        }
    }
}

dependencies {
    implementation(project(":designSystem"))
    implementation(project(":detectImageContent"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.work.runtime.ktx)
    debugImplementation(libs.ui.tooling)

    implementation("androidx.compose.foundation:foundation")
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.androidx.navigation.compose)

    // Koin
    implementation(libs.koin.androidx.compose)
    api(libs.koin.annotations)
    implementation(libs.koin.android)

    //coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")

}