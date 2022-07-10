object MultiplatformDependencies {

    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    const val kotlinxJvm ="org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.kotlinxCoroutines}"

    const val kotlinxSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"

    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"

    const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktoriOS = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val ktorJvm = "io.ktor:ktor-client-java:${Versions.ktor}"

    const val ktorServerCore="io.ktor:ktor-server-core:${Versions.ktor}"
    const val ktorServerNetty="io.ktor:ktor-server-netty:${Versions.ktor}"

    const val apolloRuntime = "com.apollographql.apollo3:apollo-runtime:${Versions.apollo}"
    const val apolloCoroutines =
        "com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo}"
    const val apolloNormalizedCache =
        "com.apollographql.apollo3:apollo-normalized-cache:${Versions.apollo}"

    const val multiplatformPaging =
        "io.github.kuuuurt:multiplatform-paging:${Versions.multiplatformPaging}"

    const val sqlDelight = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    const val sqlDelightCoroutine =
        "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    const val sqlDelightAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    const val sqlDelightNative = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    const val sqlDelightJVM = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"

    const val napier = "io.github.aakira:napier:${Versions.napier}"

    const val multiplatformSettings =
        "com.russhwolf:multiplatform-settings-no-arg:${Versions.multiplatformSettings}"
    const val multiplatformSettingsCoroutines =
        "com.russhwolf:multiplatform-settings-coroutines:${Versions.multiplatformSettings}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"

}
