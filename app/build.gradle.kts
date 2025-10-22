plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.compose") // 🔥 obligatorio en Kotlin 2.0+
}

android {
    namespace = "com.example.metrolimago"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.metrolimago"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin {
        jvmToolchain(17)
    }

}


dependencies {
    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.10.01")) // ✅ BOM oficial (maneja versiones automáticamente)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3") // ✅ Tema Material 3 completo
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    // Jetpack Compose Material3
    implementation("androidx.compose.material3:material3:1.3.1")

// Necesario para Material icons
    implementation("androidx.compose.material:material-icons-extended:1.7.3")
    // Retrofit + Gson Converter
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// Coroutines para llamadas asíncronas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

// ViewModel y LiveData (para Compose)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")



}


