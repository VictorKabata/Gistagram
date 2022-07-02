package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.TokenEntity
import com.vickikbt.shared.data.network.rest.models.TokenDto

internal fun TokenDto.toEntity(): TokenEntity {
    return TokenEntity(
        accessToken = this.accessToken,
        scope = this.scope,
        tokenType = this.tokenType,
    )
}
