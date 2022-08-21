plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
}

android {
    compileSdk = AndroidSdk.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }
}

kotlin {
    android()
    jvm()

    sourceSets {
        sourceSets["commonMain"].dependencies {
        }

        sourceSets["commonTest"].dependencies {
        }

        sourceSets["androidMain"].dependencies {
        }

        sourceSets["androidTest"].dependencies {
        }

        sourceSets["jvmMain"].dependencies {
        }

        sourceSets["jvmTest"].dependencies {
        }
    }
}
