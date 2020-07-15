plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.0")

    defaultConfig {
        applicationId = "io.github.rosariopfernandes.minibrothereye"
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val kotlinVersion = rootProject.extra.get("kotlin_version") as String
    val hiltVersion = rootProject.extra.get("hilt_version") as String

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.google.android.material:material:1.2.0-beta01")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.paging:paging-runtime-ktx:3.0.0-alpha02")
    implementation("androidx.preference:preference-ktx:1.1.1")

    // Room (Offline Persistence)
    implementation("androidx.room:room-runtime:2.3.0-alpha01")
    implementation("androidx.room:room-ktx:2.3.0-alpha01") // support for coroutines
    kapt("androidx.room:room-compiler:2.3.0-alpha01")

    // Retrofit (Networking)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coil (Image Loading)
    implementation("io.coil-kt:coil:0.11.0")

    // Coroutines (Asynchronous Operations)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6")

    // Hilt (Dependency Injection)
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha01")

    implementation("com.ramijemli.percentagechartview:percentagechartview:0.3.1")

    // For Unit Tests
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")
    testImplementation("org.mockito:mockito-core:3.3.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("androidx.test:core-ktx:1.2.0")
    testImplementation("androidx.test.ext:junit-ktx:1.1.1")
    testImplementation("org.robolectric:robolectric:4.3.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.7.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

}