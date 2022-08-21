plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
}

android {
    compileSdk = AndroidSdk.compileSdkVersion

    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion

        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = AndroidSdk.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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

    packagingOptions {
        jniLibs {
            excludes += setOf("META-INF/licenses/**")
        }
        resources {
            excludes += setOf("META-INF/licenses/**", "META-INF/AL2.0", "META-INF/LGPL2.1")
        }
    }
}

dependencies {

    implementation(project(":shared"))
    implementation(project(":core:library"))

    implementation(AndroidDependencies.androidCore)
    implementation(AndroidDependencies.appCompat)

    implementation(AndroidDependencies.material)

    implementation(AndroidDependencies.composeUi)
    implementation(AndroidDependencies.composeMaterial)
    implementation(AndroidDependencies.composeTooling)
    // androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    implementation(AndroidDependencies.composeLiveData)
    // debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    implementation(AndroidDependencies.composeActivity)

    implementation(AndroidDependencies.lifeCycleRuntime)

    // Koin-Dependency injection
    implementation(AndroidDependencies.koinAndroid)
    implementation(AndroidDependencies.koinCompose)

    // Accompanist Libs
    implementation(AndroidDependencies.accompanistNavigationAnimation)
    implementation(AndroidDependencies.accompanistFlowLayout)

    // Splash Screen API
    implementation(AndroidDependencies.splashScreen)

    // Coil-Image Loader
    implementation(AndroidDependencies.coil)

    // Compose Navigation-Navigation between various screens
    implementation(AndroidDependencies.navigation)

    implementation(AndroidDependencies.composeMarkDown)
    implementation(AndroidDependencies.markDownView)
}
