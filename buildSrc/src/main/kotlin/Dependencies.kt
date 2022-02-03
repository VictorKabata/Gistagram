object Dependencies {
    const val kotlin = "1.5.30"

    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeCompiler = "1.5.21"
    const val composeConstraint = "1.0.0-rc01"

    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val accompanistNavigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val palette = "28.0.0"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val paging = "3.0.0"
    const val coroutines = "1.5.2"
    const val firebaseBOM = "29.0.3"
    const val ratingBar = "1.1.1"
    const val pagingCompose = "1.0.0-alpha14"
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

    const val apolloRuntime = "com.apollographql.apollo:apollo-runtime:${Versions.apollo}"
    const val apolloCoroutines =
        "com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val jUnit = "4.13.2"
    const val truth = "1.1.3"
    const val coroutinesTest = "1.5.0"
    const val robolectic = "4.5.1"
    const val mockWebServer = "4.7.2"
}