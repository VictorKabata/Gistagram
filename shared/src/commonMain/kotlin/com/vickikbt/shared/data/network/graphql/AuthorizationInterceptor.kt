package com.vickikbt.shared.data.network.graphql

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.vickikbt.shared.data.cache.sqldelight.dao.AccessTokenDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class AuthorizationInterceptor constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val accessTokenDao: AccessTokenDao
) : HttpInterceptor {

    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {

        val token = withContext(ioDispatcher) {
            accessTokenDao.getAccessToken.first()?.accessToken
        }

        return chain.proceed(
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}
