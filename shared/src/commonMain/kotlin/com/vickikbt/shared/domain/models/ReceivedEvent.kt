package com.vickikbt.shared.domain.models


data class ReceivedEvent(
    val id: String?,
    val type: String,
    val actor: Actor?,
    val repo: Repo?,
    val payload: Any?,
    val public: Boolean,
    val createdAt: String
)
