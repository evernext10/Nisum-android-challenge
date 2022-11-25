plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Module.core))
    implementation(project(Module.Navigation))
    implementation(project(Module.MarketplaceProductDetailDomain))

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)

    implementation(Coroutines.coroutinesCore)
    implementation(Coroutines.coroutinesAndroid)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(UI.material)
    implementation(UI.constraintLayout)
    implementation(UI.navigationFragmentKtx)
    implementation(UI.navigationUiKtx)

    implementation(UI.lifecycleExtensions)
    implementation(UI.lifecycleViewModelKtx)
    implementation(UI.lifecycleViewModelSavedState)
    implementation(UI.lifecycleRuntimeKtx)
    implementation(UI.lifecycleLivedataKtx)

    implementation(Glide.glide)
    kapt(Glide.glideCompiler)
    implementation(Glide.glideOkhttp)

    debugImplementation(Build.leakCanary)

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
    androidTestImplementation(Testing.testDexmaker)
    androidTestImplementation(Testing.testDexmaker)
    androidTestImplementation(Retrofit.gsonConverter)
}
