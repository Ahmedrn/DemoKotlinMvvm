@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.skom.demokotlinmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.skom.demokotlinmvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.viewBinding = true

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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit)
    //Hilt
    implementation(libs.daggerHilt)
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    //Moshi
    implementation(libs.moshi)
    implementation(libs.moshiRetrofitConverter)
    //ViewModel
    implementation(libs.viewModel)
    implementation(libs.activityKtx)
    implementation(libs.coil)
    implementation(libs.materialDesign)
    implementation(libs.swipeRefreshLayout)
    implementation(libs.materialDialog)

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    kapt(libs.roomCompiler)


    testImplementation(libs.junit)
    testImplementation(libs.room)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.mockkAndroid)
}
