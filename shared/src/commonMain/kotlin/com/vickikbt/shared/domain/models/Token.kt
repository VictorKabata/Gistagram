package com.vickikbt.shared.domain.models

data class Token(
    val accessToken: String? = null,
    val scope: String? = null,
    val tokenType: String? = null
)
