package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.String

public data class AccessTokenEntity(
  public val accessToken: String,
  public val scope: String?,
  public val tokenType: String?
) {
  public override fun toString(): String = """
  |AccessTokenEntity [
  |  accessToken: $accessToken
  |  scope: $scope
  |  tokenType: $tokenType
  |]
  """.trimMargin()
}
