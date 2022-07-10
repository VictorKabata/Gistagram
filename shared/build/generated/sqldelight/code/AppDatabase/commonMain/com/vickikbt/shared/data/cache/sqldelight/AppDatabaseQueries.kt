package com.vickikbt.shared.`data`.cache.sqldelight

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getAccessToken(mapper: (
    accessToken: String,
    scope: String?,
    tokenType: String
  ) -> T): Query<T>

  public fun getAccessToken(): Query<AccessTokenEntity>

  public fun saveAccessToken(AccessTokenEntity: AccessTokenEntity): Unit

  public fun deleteAccessToken(): Unit
}
