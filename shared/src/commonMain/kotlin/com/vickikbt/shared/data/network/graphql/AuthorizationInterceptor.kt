package com.vickikbt.shared.data.network.graphql

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain

class AuthorizationInterceptor : HttpInterceptor {

    // ToDo: Get token saved in local cache

    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        return chain.proceed(
            request.newBuilder()
                .addHeader("Authorization", "Bearer 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")
                .build()
        )
    }
}
