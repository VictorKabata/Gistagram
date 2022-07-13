package com.vickikbt.shared.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.LoggingInterceptor
import com.vickikbt.shared.data.cache.multiplatform_settings.PreferenceManager
import com.vickikbt.shared.data.cache.realm.dao.UserDao
import com.vickikbt.shared.data.cache.realm.models.PlanEntity
import com.vickikbt.shared.data.cache.realm.models.UserEntity
import com.vickikbt.shared.data.cache.sqldelight.dao.AccessTokenDao
import com.vickikbt.shared.data.data_source.AuthRepositoryImpl
import com.vickikbt.shared.data.data_source.ProfileRepositoryImpl
import com.vickikbt.shared.data.data_source.SettingsRepositoryImpl
import com.vickikbt.shared.data.network.graphql.AuthorizationInterceptor
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.data.network.rest.ApiClientImpl
import com.vickikbt.shared.domain.repositories.AuthRepository
import com.vickikbt.shared.domain.repositories.ProfileRepository
import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.domain.utils.Constants
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.e(tag = "Http Client", message = message)
                    }
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }
    single<ApiClient> { ApiClientImpl(httpClient = get()) }

    /**
     *Create instance of realm config need to
     * instantiate realm db instance that is
     * provided to DAOs through constructor injection
     */
    single {
        val configs =
            RealmConfiguration.Builder(schema = setOf(UserEntity::class, PlanEntity::class)).build()
        Realm.open(configuration = configs)
    }
    single { UserDao(realmDatabase = get(), ioDispatcher = get(named("IODispatcher"))) }

    /**
     * Creates instance of AuthorizationInterceptor that is used to authenticate network
     * calls to GitHub graphql API made by Apollo and provided to the Apollo Client instance
     * as a HttpInterceptor
     */
    single(named("IODispatcher")) { Dispatchers.Default }

    single {
        ApolloClient.Builder()
            .serverUrl(Constants.GRAPHQL_BASE_URL)
            .addHttpInterceptor(LoggingInterceptor())
            .addHttpInterceptor(
                AuthorizationInterceptor(
                    ioDispatcher = get(named("IODispatcher")),
                    accessTokenDao = get()
                )
            )
            .build()
    }

    /**
     * Create instance of database abstract objects
     */
    single { AccessTokenDao(databaseDriverFactory = get()) }

    single { PreferenceManager(multiplatformSettingsWrapper = get()) }

    /**
     * Injecting to repositories
     */
    single<AuthRepository> {
        AuthRepositoryImpl(
            apiClient = get(),
            tokenDao = get(),
            userDao = get()
        )
    }
    single<ProfileRepository> { ProfileRepositoryImpl(apolloClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(preferenceManager = get()) }
}

expect fun platformModule(): Module
