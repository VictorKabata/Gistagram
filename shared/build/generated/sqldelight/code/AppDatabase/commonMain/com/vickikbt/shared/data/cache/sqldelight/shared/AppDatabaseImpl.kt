package com.vickikbt.shared.`data`.cache.sqldelight.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.shared.`data`.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.`data`.cache.sqldelight.AppDatabase
import com.vickikbt.shared.`data`.cache.sqldelight.AppDatabaseQueries
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE AccessTokenEntity(
          |accessToken TEXT NOT NULL PRIMARY KEY,
          |scope TEXT DEFAULT NULL,
          |tokenType TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val getAccessToken: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getAccessToken(mapper: (
    accessToken: String,
    scope: String?,
    tokenType: String
  ) -> T): Query<T> = Query(-684916433, getAccessToken, driver, "AppDatabase.sq", "getAccessToken",
      "SELECT * FROM AccessTokenEntity") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1),
      cursor.getString(2)!!
    )
  }

  public override fun getAccessToken(): Query<AccessTokenEntity> = getAccessToken { accessToken,
      scope, tokenType ->
    AccessTokenEntity(
      accessToken,
      scope,
      tokenType
    )
  }

  public override fun saveAccessToken(AccessTokenEntity: AccessTokenEntity): Unit {
    driver.execute(1165549512, """
    |INSERT OR REPLACE INTO AccessTokenEntity(accessToken,scope,tokenType)
    |VALUES (?, ?, ?)
    """.trimMargin(), 3) {
      bindString(1, AccessTokenEntity.accessToken)
      bindString(2, AccessTokenEntity.scope)
      bindString(3, AccessTokenEntity.tokenType)
    }
    notifyQueries(1165549512, {database.appDatabaseQueries.getAccessToken})
  }

  public override fun deleteAccessToken(): Unit {
    driver.execute(1944710874, """DELETE FROM AccessTokenEntity""", 0)
    notifyQueries(1944710874, {database.appDatabaseQueries.getAccessToken})
  }
}
