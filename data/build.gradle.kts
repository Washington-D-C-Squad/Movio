import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
}

android {
    namespace = "com.madrid.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    android.buildFeatures.buildConfig = true
    val properties = Properties()
    properties.load(rootProject.file("secret.properties").inputStream())
    properties.getProperty("API_KEY")
    buildTypes {
        release {
            buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
            buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.kotlinx.datetime)

    implementation(project(":domain"))
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("io.ktor:ktor-client-core:2.3.13")
    implementation("io.ktor:ktor-client-cio:2.3.13")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.13")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
    implementation("io.insert-koin:koin-core:3.5.3")
    implementation("io.insert-koin:koin-android:3.5.3")

    val room_version = "2.7.2"
    implementation("androidx.room:room-runtime:$room_version")
    // use Kotlin Symbol Processing (KSP
    ksp("androidx.room:room-compiler:$room_version")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    // Test helpers
    testImplementation("androidx.room:room-testing:${room_version}")
    //  Paging 3 Integration
    implementation("androidx.room:room-paging:${room_version}")
}