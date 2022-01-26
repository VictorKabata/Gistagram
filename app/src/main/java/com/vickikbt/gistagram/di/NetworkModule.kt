package com.vickikbt.gistagram.di

import com.apollographql.apollo.ApolloClient
import com.vickikbt.gistagram.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        ApolloClient.builder()
            .serverUrl(Constants.BASE_URL)
            .okHttpClient(provideOkHttpClient())
            .build()
    }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}