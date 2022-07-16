package com.vickikbt.shared.data.cache.sqldelight.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.data.cache.sqldelight.AppDatabase
import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.map

class AccessTokenDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = appDatabase.appDatabaseQueries

    /**
     * Save access token returned from network call to SQLDelight database
     */
    suspend fun saveAccessToken(accessTokenEntity: AccessTokenEntity) {
        dbQuery.transaction {
            dbQuery.saveAccessToken(accessTokenEntity)
        }
    }

    /**
     * Returns all data store in access token entity table in SQLDelight database
     * as a flow
     */
    val getAccessToken = dbQuery.getAccessToken().asFlow().map { it.executeAsOneOrNull() }

    /**
     * Deletes all data in access token entity table in SQLDelight database
     */
    fun deleteAccessToken() = dbQuery.deleteAccessToken()

}
