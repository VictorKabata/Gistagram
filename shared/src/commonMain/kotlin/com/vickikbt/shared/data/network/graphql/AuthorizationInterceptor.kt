package com.vickikbt.shared.data.network.graphql

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class AuthorizationInterceptor constructor(private val ioDispatcher: CoroutineDispatcher) :
    HttpInterceptor {

    // ToDo: Get token saved in local cache
    val tokenFlow = flowOf("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")

    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        val token = withContext(ioDispatcher) {
            tokenFlow.first()
        }

        return chain.proceed(
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}
