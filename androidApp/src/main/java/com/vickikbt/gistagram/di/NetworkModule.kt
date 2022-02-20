package com.vickikbt.gistagram.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    /* single {
         ApolloClient.builder()
             .serverUrl(Constants.BASE_URL)
             .okHttpClient(provideOkHttpClient())
             .build()
     }*/
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideOkHttpClient(): OkHttpClient {

    return OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer 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") // ToDo: Add to Git Igore
                .build()
            chain.proceed(newRequest)
        }
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}
