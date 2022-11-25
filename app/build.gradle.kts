plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.appId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

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

    implementation(UI.lifecycleExtensions)
    implementation(UI.lifecycleViewModelKtx)
    implementation(UI.lifecycleViewModelSavedState)
    implementation(UI.lifecycleRuntimeKtx)
    implementation(UI.lifecycleLivedataKtx)

    debugImplementation(Build.leakCanary)

    implementation(Glide.glide)
    kapt(Glide.glideCompiler)
    implementation(Glide.glideOkhttp)
    implementation(project(Module.core))
    implementation(project(Module.Navigation))

    implementation(project(Module.MarketplaceProductListData))
    implementation(project(Module.MarketplaceProductListDomain))
    implementation(project(Module.MarketplaceProductListPresentation))

    implementation(project(Module.MarketplaceProductDetailData))
    implementation(project(Module.MarketplaceProductDetailDomain))
    implementation(project(Module.MarketplaceProductDetailPresentation))

    implementation(Koin.koinCore)
    implementation(Koin.koinAndroid)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.testRunner)
}
