plugins {
    id("com.android.application")
}

android {
    namespace = "com.ujjwaltechnolabs.rolapartner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ujjwaltechnolabs.rolapartner"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.9.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("com.google.firebase:firebase-messaging-ktx:23.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Lottie Animation
    implementation("com.airbnb.android:lottie:6.1.0")
    //Country Picker
    implementation("com.hbb20:ccp:2.6.0")
    //Phone nUmber Validation
    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.32")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    // Add ViewModel and LiveData dependencies
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    //Google Map
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.maps:google-maps-services:0.17.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.maps.android:android-maps-utils:2.2.1")
    //for SMS
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    //Camera
    implementation("androidx.camera:camera-core:1.3.1")
    implementation("androidx.camera:camera-camera2:1.3.1")
    implementation("androidx.camera:camera-lifecycle:1.3.1")
    implementation("androidx.camera:camera-view:1.3.1")
    //image crop
  // implementation ("com.theartofdev.edmodo:android-image-cropper:2.8.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")

    //bottom bar
    implementation("com.github.ertugrulkaragoz:SuperBottomBar:0.4")

    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

    implementation("com.github.yalantis:ucrop:2.2.8")

}