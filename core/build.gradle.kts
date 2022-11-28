plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
        viewBinding = true
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(UI.material)
    implementation(UI.constraintLayout)
    implementation(UI.navigationFragmentKtx)
    implementation(UI.navigationUiKtx)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.gsonConverter)

    implementation(UI.lifecycleExtensions)
    implementation(UI.lifecycleViewModelKtx)
    implementation(UI.lifecycleViewModelSavedState)
    implementation(UI.lifecycleRuntimeKtx)
    implementation(UI.lifecycleLivedataKtx)

    implementation(Glide.glide)
    kapt(Glide.glideCompiler)
    implementation(Glide.glideOkhttp)

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)

    implementation(Room.roomRuntime)
    kapt(Room.roomCompiler)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)
    testImplementation(Testing.testArchCore)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.testRunner)
    androidTestImplementation(Testing.testArchCore)
}
