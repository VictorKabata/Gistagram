package com.vickikbt.shared.data.network.rest

import com.vickikbt.shared.data.network.rest.models.AccessTokenDto
import com.vickikbt.shared.domain.utils.Configs
import com.vickikbt.shared.domain.utils.Constants

interface ApiClient {

    suspend fun fetchAccessToken(
        clientId: String = Configs.CLIENT_ID,
        clientSecret: String = Configs.CLIENT_SECRET,
        code: String
    ): AccessTokenDto?
}
