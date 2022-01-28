plugins {
    kotlin("multiplatform")
    id("com.android.library")
    //id("com.apollographql.apollo3")
}

kotlin {
    android()

    /*listOf(
        iosX64(),
        iosArm64()
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }*/

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") {
                    isForce = true
                }

                // koin
                /*api(Koin.core)

                api(Deps.apolloRuntime)
                api(Deps.apolloNormalizedCache)
                api(Deps.multiplatformPaging)*/
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

        /*val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
        }*/

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


/*apollo {
    packageName.set("com.vickikbt.gistagram")
    generateOptionalOperationVariables.set(false)
}*/
