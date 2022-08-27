plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.atitienei_daniel.core_ui"
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
    implementation(project(":core-model"))

    implementation(Dependencies.iconsExtended)
    implementation(Dependencies.googleFonts)

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.material3)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
    debugImplementation(Dependencies.composeUiTestManifest)
}