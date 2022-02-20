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
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
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

    // KMM Libraries
    const val coroutinesKmm =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesKmm}"

    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"

    const val kotlinxSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"

    const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktoriOS = "io.ktor:ktor-client-ios:${Versions.ktor}"

    const val apolloRuntime = "com.apollographql.apollo3:apollo-runtime:${Versions.apollo}"
    const val apolloCoroutines =
        "com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}"
    const val apolloNormalizedCache =
        "com.apollographql.apollo3:apollo-normalized-cache:${Versions.apollo}"

    const val multiplatformPaging =
        "io.github.kuuuurt:multiplatform-paging:${Versions.multiplatformPaging}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // const val realm = "io.realm.kotlin:library-base:${Versions.realm}"

    const val sqlDelight = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val sqlDelightCoroutines =
        "com.squareup.sqldelight:coroutines-extensions-jvm:${Versions.sqlDelight}"
    const val sqlDelightAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    const val sqlDelightiOS = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"

    const val cabretLog = "de.jensklingenberg.cabret:cabret-log:${Versions.cabretLog}"
    const val cabretLogAndroid =
        "de.jensklingenberg.cabret:cabret-log-android:${Versions.cabretLog}"
    const val cabretLogiOS = "de.jensklingenberg.cabret:cabret-log-iosx64:${Versions.cabretLog}"
    const val cabretLogJvm = "de.jensklingenberg.cabret:cabret-log-jvm:${Versions.cabretLog}"

    const val jUnit = "4.13.2"
    const val truth = "1.1.3"
    const val coroutinesTest = "1.5.0"
    const val robolectic = "4.5.1"
    const val mockWebServer = "4.7.2"
}
