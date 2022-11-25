object Build {
    private const val androidBuildToolsVersion = "7.2.1"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    private const val leakCanaryVersion = "2.10"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
}
