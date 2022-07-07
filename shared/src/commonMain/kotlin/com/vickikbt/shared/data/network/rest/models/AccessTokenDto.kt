package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenDto(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("scope")
    val scope: String,

    @SerialName("token_type")
    val tokenType: String
)
