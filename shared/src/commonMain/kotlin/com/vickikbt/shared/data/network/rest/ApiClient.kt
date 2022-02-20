package com.vickikbt.shared.data.network.rest

import com.vickikbt.shared.data.network.rest.models.TokenDto

interface ApiClient {

    suspend fun getUserToken(clientId: String, clientSecret: String, code: String): TokenDto?
}
