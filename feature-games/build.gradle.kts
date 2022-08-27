plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.atitienei_daniel.feature_games"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":core-designsystem"))
    implementation(project(":core-ui"))
    implementation(project(":core-model"))
    implementation(project(":core-data"))
    implementation(project(":core-navigation"))
    implementation(project(":core-datastore"))

    implementation(Dependencies.navigationCompose)

    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.navigationCompose)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAndroidCompiler)

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.material3)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.lifecycleRuntimeCompose)
    implementation(Dependencies.lifecycleViewModelCompose)

    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
}