plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk 33

    defaultConfig {
        minSdk 28
        targetSdk 33

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles "consumer-rules.pro"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion compose_version
    }
}

dependencies {
    implementation(project(":core-designsystem"))
    implementation(project(":core-ui"))
    implementation(project(":core-model"))
    implementation(project(":core-data"))
    implementation(project(":core-navigation"))
    implementation(project(":core-datastore"))

    implementation("com.google.dagger:hilt-android:2.42")
    implementation 'androidx.appcompat:appcompat:1.5.0'
    implementation 'com.google.android.material:material:1.6.1'
    kapt("com.google.dagger:hilt-android-compiler:2.42")
    implementation 'androidx.hilt:hilt-navigation-compose:1.0.0'
    kapt "androidx.hilt:hilt-compiler:1.0.0"

    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version"

    implementation "androidx.compose.ui:ui:1.2.1"
    implementation "androidx.compose.ui:ui-tooling-preview:1.2.1"
    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.compose.material3:material3:1.0.0-beta01'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation 'androidx.activity:activity-compose:1.5.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    androidTestImplementation "androidx.compose.ui:ui-test-junit4:1.2.1"
    debugImplementation "androidx.compose.ui:ui-tooling:1.2.1"
    debugImplementation "androidx.compose.ui:ui-test-manifest:1.2.1"
}