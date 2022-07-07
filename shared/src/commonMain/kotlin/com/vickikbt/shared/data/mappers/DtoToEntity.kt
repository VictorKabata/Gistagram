package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.data.network.rest.models.AccessTokenDto

internal fun AccessTokenDto.toEntity(): AccessTokenEntity {
    return AccessTokenEntity(
        accessToken = this.accessToken,
        scope = this.scope,
        tokenType = this.tokenType,
    )
}
