object Dependencies {
    val navigationCompose by lazy {
        "androidx.navigation:navigation-compose:${Versions.navigation}"
    }
    val lifecycleViewModelCompose by lazy {
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    }
    val lifecycleViewModelKtx by lazy {
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }
    val lifecycleRuntimeCompose by lazy {
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
    }
    val lifecycleRuntimeKtx by lazy {
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    }
    val hiltAndroid by lazy {
        "com.google.dagger:hilt-android:${Versions.hilt}"
    }
    val hiltAndroidCompiler by lazy {
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }
    val hiltNavigationCompose by lazy {
        "androidx.hilt:hilt-navigation-compose:${Versions.androidxHilt}"
    }
    val hiltCompiler by lazy {
        "androidx.hilt:hilt-compiler:${Versions.androidxHilt}"
    }
    val composeUi by lazy {
        "androidx.compose.ui:ui:${Versions.composeUi}"
    }
    val composeUiToolingPreview by lazy {
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}"
    }
    val coreKtx by lazy {
        "androidx.core:core-ktx:${Versions.core}"
    }
    val material3 by lazy {
        "androidx.compose.material3:material3:${Versions.material3}"
    }
    val activityCompose by lazy {
        "androidx.activity:activity-compose:${Versions.activity}"
    }
    val jUnit by lazy {
        "junit:junit:${Versions.jUnit}"
    }
    val espressoCore by lazy {
        "androidx.test.espresso:espresso-core:${Versions.espresso}}"
    }
    val composeUiTooling by lazy {
        "androidx.compose.ui:ui-tooling:${Versions.composeUi}"
    }
    val composeUiTestManifest by lazy {
        "androidx.compose.ui:ui-test-manifest:${Versions.composeUi}"
    }
    val composeUiTestJUnit by lazy {
        "androidx.compose.ui:ui-test-junit4:${Versions.composeUi}"
    }
}