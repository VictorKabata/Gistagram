object AndroidSdk {
    const val appName = "Gistagram"

    const val applicationId = "com.vickikbt.gistagram"

    const val buildToolVersion = "30.0.3"

    const val minSdkVersion = 21
    const val compileSdkVersion = 31
    const val targetSdkVersion = compileSdkVersion

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    // const val composeCompiler = "1.5.21"
    // const val composeConstraint = "1.0.0-rc01"

    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val coroutines = "1.5.2"
    const val firebaseBOM = "29.0.3"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    const val composeMarkDown = "com.github.jeziellago:compose-markdown:${Versions.composeMarkDown}"
    const val markDownView = "com.github.tiagohm.MarkdownView:library:${Versions.markDownView}"
}
