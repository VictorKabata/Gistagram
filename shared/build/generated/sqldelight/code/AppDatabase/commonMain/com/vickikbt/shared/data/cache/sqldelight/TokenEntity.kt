package com.vickikbt.shared.`data`.cache.sqldelight

import kotlin.String

public data class TokenEntity(
  public val accessToken: String,
  public val scope: String?,
  public val tokenType: String
) {
  public override fun toString(): String = """
  |TokenEntity [
  |  accessToken: $accessToken
  |  scope: $scope
  |  tokenType: $tokenType
  |]
  """.trimMargin()
}
