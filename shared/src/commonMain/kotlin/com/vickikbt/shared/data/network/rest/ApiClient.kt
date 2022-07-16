package com.vickikbt.shared.data.network.rest

import com.vickikbt.shared.data.network.rest.models.AccessTokenDto
import com.vickikbt.shared.data.network.rest.models.UserDto
import com.vickikbt.shared.domain.utils.Configs

interface ApiClient {

    suspend fun fetchAccessToken(
        clientId: String = Configs.CLIENT_ID,
        clientSecret: String = Configs.CLIENT_SECRET,
        code: String
    ): AccessTokenDto?

    suspend fun fetchUserProfile(accessToken: String): UserDto?
}
