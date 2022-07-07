package com.vickikbt.shared.`data`.cache.sqldelight

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getToken(mapper: (
    accessToken: String,
    scope: String?,
    tokenType: String?
  ) -> T): Query<T>

  public fun getToken(): Query<AccessTokenEntity>

  public fun saveToken(AccessTokenEntity: AccessTokenEntity): Unit

  public fun deleteToken(): Unit
}
