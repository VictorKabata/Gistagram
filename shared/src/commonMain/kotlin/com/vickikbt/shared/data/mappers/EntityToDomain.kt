package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.TokenEntity
import com.vickikbt.shared.domain.models.Token

internal fun TokenEntity.toDomain(): Token {
    return Token(
        accessToken = this.accessToken,
        scope = this.scope,
        tokenType = this.tokenType
    )
}
