/*
 * BU UYGULAMA MODÜLÜNÜN (app) İNŞA TALİMATIDIR.
 * "viewBinding = true" en önemlisidir.
 */
plugins {
    // Bu satır, bunun bir Android Uygulaması olduğunu belirtir
    alias(libs.plugins.android.application)
    // Bu satır, Kotlin dilini kullandığımızı belirtir
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.w3ruslan.gunsonuapp" // Manifest'teki paket adıyla aynı olmalı
    compileSdk = 34 // Android 14 SDK'sı

    defaultConfig {
        applicationId = "com.w3ruslan.gunsonuapp"
        minSdk = 24 // Android 7.0 (Nougat) - Konuştuğumuz gibi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    // !!! EN ÖNEMLİ BÖLÜM !!!
    // XML (tasarım) ile Kotlin (beyin) arasındaki "binding"i etkinleştirir.
    buildFeatures {
        viewBinding = true
    }
}

// GEREKLİ KÜTÜPHANELER
dependencies {
    // Bu, Android'in temel çekirdek kütüphanesidir
    implementation(libs.androidx.core.ktx)
    // Bu, AppCompatActivity'nin (ana ekranımız) çalışmasını sağlar
    implementation(libs.androidx.appcompat)
    // Bu, Google'ın Materyal Tasarım bileşenlerini sağlar
    implementation(libs.google.android.material)
    
    // !!! CARDVIEW KÜTÜPHANESİ !!!
    // Tasarımdaki <androidx.cardview.widget.CardView> etiketinin çalışması için bu şart.
    implementation(libs.androidx.cardview)
}
