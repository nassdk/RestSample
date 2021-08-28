plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.rest"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-alpha02"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")

    implementation("androidx.compose.ui:ui:1.1.0-alpha02")
    implementation("androidx.compose.ui:ui-util:1.1.0-alpha02")
    implementation("androidx.compose.material:material:1.1.0-alpha02")
    implementation("androidx.compose.ui:ui-tooling:1.1.0-alpha02")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha07")

    implementation("com.google.android.material:material:1.4.0")

    implementation("com.google.accompanist:accompanist-coil:0.14.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.17.0")
    implementation("com.google.accompanist:accompanist-pager:0.17.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.17.0")
}