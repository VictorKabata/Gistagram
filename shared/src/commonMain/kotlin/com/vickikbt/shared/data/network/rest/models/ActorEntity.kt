package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorEntity(
    @SerialName("id")
    val id: Int?,

    @SerialName("login")
    val login: String?,

    @SerialName("display_login")
    val displayLogin: String?,

    @SerialName("url")
    val url: String?,

    @SerialName("avatar_url")
    val avatarUrl: String?
)
