package com.vickikbt.shared.di

import com.apollographql.apollo3.ApolloClient
import com.vickikbt.shared.data.data_source.ProfileRepositoryImpl
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.data.network.rest.ApiClientImpl
import com.vickikbt.shared.domain.repositories.ProfileRepository
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module

val commonModule = module {

    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) { serializer = KotlinxSerializer() }
        }
    }
    single<ApiClient> { ApiClientImpl(httpClient = get()) }

    /**
     *Create instance of realm config need to
     * instantiate realm db instance that is
     * provided to DAOs through constructor injection
     */
    // single { RealmConfiguration.with(schema = setOf(TokenEntity::class)) }
    // single { Realm.open(configuration = get()) }
    // single { TokenDao(appDatabase = get()) }

    /**
     * Creates instance of AuthorizationInterceptor that is used to authenticate network
     * calls to GitHub graphql API made by Apollo and provided to the Apollo Client instance
     * as a HttpInterceptor
     */
    single {
        ApolloClient.Builder()
            .serverUrl("https://api.github.com/graphql")
            .addHttpHeader("Authorization", "Bearer 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")
            // .addHttpInterceptor(AuthorizationInterceptor())
            .build()
    }

    /**
     * Injecting to repositories
     */
    // single<AuthRepository> { AuthRepositoryImpl(apiClient = get(), tokenDao = get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(apolloClient = get()) }
}
