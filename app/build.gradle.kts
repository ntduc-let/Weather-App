import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.android.jetpack.compose.ntduc.weather"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.android.jetpack.compose.ntduc.weather"

        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()

        val versionMajor = 1
        val versionMinor = 0
        val versionPatch = 0
        versionCode = (versionMajor * 100 + versionMinor * 10 + versionPatch)
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        val date = SimpleDateFormat("dd.MM.yyyy").format(Date())
        val hour = SimpleDateFormat("HH").format(Date())
        val minutes = SimpleDateFormat("mm").format(Date())
        base.archivesName = "Weather_App_V${versionName}_${date}_${hour}h${minutes}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        multiDexEnabled = true
    }

    signingConfigs {
        val keyAlias = "weather_app"
        val keyPassword = "Let22042000"
        val keystoreFile = file("weather_app_keystore.jks")
        val storePassword = "Let22042000"

        getByName("debug") {
            this.keyAlias = keyAlias
            this.keyPassword = keyPassword
            this.storeFile = keystoreFile
            this.storePassword = storePassword
        }

        create("release") {
            this.keyAlias = keyAlias
            this.keyPassword = keyPassword
            this.storeFile = keystoreFile
            this.storePassword = storePassword
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

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
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Hilt: https://dagger.dev/hilt/
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // For instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)

    // For local unit tests
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler)

    // Retrofit: https://github.com/square/retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    // Okhttp: https://square.github.io/okhttp/
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Location
    implementation(libs.play.services.location)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation(libs.firebase.config.ktx)
    implementation(libs.firebase.messaging.ktx)
//    implementation(libs.firebase.database.ktx)
//    implementation(libs.firebase.auth.ktx)

    // Navigation: https://developer.android.com/jetpack/compose/navigation
    implementation(libs.androidx.navigation.compose)

    // Hawk: https://github.com/orhanobut/hawk
    implementation(libs.hawk)

    // Lottie: https://github.com/airbnb/lottie/blob/master/android-compose.md
    implementation(libs.lottie.compose)
}