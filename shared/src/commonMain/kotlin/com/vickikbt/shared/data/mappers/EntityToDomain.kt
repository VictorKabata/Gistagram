package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.domain.models.AccessToken

internal fun AccessTokenEntity.toDomain(): AccessToken {
    return AccessToken(
        accessToken = this.accessToken,
        scope = this.scope,
        tokenType = this.tokenType
    )
}
