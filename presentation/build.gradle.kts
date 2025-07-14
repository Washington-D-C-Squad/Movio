plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
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
    implementation(libs.koin.compose.viewmodel)
    implementation(project(":designSystem"))
    implementation(project(":detectImageContent"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.androidx.navigation.compose)
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")

    implementation("io.insert-koin:koin-android:3.5.3")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")

}