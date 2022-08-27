plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.atitienei_daniel.core_model"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.coreKtx)
}