plugins {
    kotlin("multiplatform")
    id("com.android.library")
     id("com.apollographql.apollo3")
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") {
                    isForce = true
                }

                // koin
                // api(Koin.core)

                api(Dependencies.apolloRuntime)
                api(Dependencies.apolloNormalizedCache)

                api(Dependencies.multiplatformPaging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.1")
            }
        }
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 30
    }
}

apollo {
    packageName.set("com.vickikbt.gistagram")
    generateOptionalOperationVariables.set(false)
}
