import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(Plugins.nativeCoroutines) version Versions.kmpNativeCoroutines
    id(Plugins.apollo).version(Versions.apollo)
    id(Plugins.sqlDelight) version Versions.sqlDelight
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

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        when {
            System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }
    iosTarget("iOS") {}

    jvm()

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(MultiplatformDependencies.kotlinxCoroutines)

            api(MultiplatformDependencies.koinCore)

            implementation(MultiplatformDependencies.kotlinxSerialization)

            implementation(MultiplatformDependencies.ktorCore)
            implementation(MultiplatformDependencies.ktorSerialization)
            implementation(MultiplatformDependencies.ktorLogging)

            implementation(MultiplatformDependencies.sqlDelight)
            implementation(MultiplatformDependencies.sqlDelightCoroutine)

            api(MultiplatformDependencies.apolloRuntime)
            api(MultiplatformDependencies.apolloNormalizedCache)

            implementation(MultiplatformDependencies.multiplatformSettings)
            implementation(MultiplatformDependencies.multiplatformSettingsCoroutines)

            api(MultiplatformDependencies.multiplatformPaging)

            implementation(MultiplatformDependencies.multiplatformSettings)
            implementation(MultiplatformDependencies.multiplatformSettingsCoroutines)

            api(MultiplatformDependencies.napier)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }

        sourceSets["androidMain"].dependencies {
            implementation(MultiplatformDependencies.ktorAndroid)
            implementation(MultiplatformDependencies.sqlDelightAndroid)
        }

        sourceSets["androidTest"].dependencies {
            implementation(kotlin("test-junit"))
            implementation("junit:junit:4.13.1")
        }

        sourceSets["iOSMain"].dependencies {
            implementation(MultiplatformDependencies.ktoriOS)
            implementation(MultiplatformDependencies.sqlDelightNative)
        }

        sourceSets["iOSTest"].dependencies {
        }

        sourceSets["jvmMain"].dependencies {
            api(MultiplatformDependencies.ktorJvm)
            implementation(MultiplatformDependencies.sqlDelightJVM)
        }

        sourceSets["jvmTest"].dependencies {
        }
    }
}

apollo {
    packageName.set("com.vickikbt.gistagram")
    generateOptionalOperationVariables.set(false)
}

sqldelight {
    database(name = "AppDatabase") {
        packageName = "com.vickikbt.shared.data.cache.sqldelight"
        sourceFolders = listOf("kotlin")
    }
}
