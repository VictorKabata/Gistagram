package com.vickikbt.shared.data.network.rest.models

import kotlinx.serialization.SerialName


data class ReceivedEventEntity(
    @SerialName("id")
    val id: String?,

    @SerialName("type")
    val type: String,

    @SerialName("actor")
    val actor: ActorEntity?,

    @SerialName("repo")
    val repo: RepoEntity?,

    @SerialName("payload")
    val payload: Any?,

    @SerialName("public")
    val public: Boolean,

    @SerialName("created_at")
    val createdAt: String
)
