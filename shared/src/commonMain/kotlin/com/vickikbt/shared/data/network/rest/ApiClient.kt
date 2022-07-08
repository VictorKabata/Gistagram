package com.vickikbt.shared.data.network.rest

import com.vickikbt.shared.data.network.rest.models.AccessTokenDto
import com.vickikbt.shared.domain.utils.Constants

interface ApiClient {

    suspend fun fetchAccessToken(
        clientId: String = Constants.CLIENT_ID,
        clientSecret: String = Constants.CLIENT_SECRET,
        code: String
    ): AccessTokenDto?
}
