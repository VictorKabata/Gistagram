package com.vickikbt.shared.data.data_source

import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.data.cache.sqldelight.dao.AccessTokenDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val accessTokenDao: AccessTokenDao
) : AuthRepository {

    override suspend fun fetchAccessToken(code: String): AccessToken? {
        val response = apiClient.fetchAccessToken(code = code)
        return try {
            val responseEntity = response?.toEntity()
            responseEntity?.let {
                saveAccessToken(accessTokenEntity = it)
            }

            responseEntity?.toDomain()
        } catch (e: Exception) {
            AccessToken() //Replace with UiState
        }

    }

    override suspend fun getAccessToken(): Flow<AccessToken?> {
        return accessTokenDao.getToken.map { it?.toDomain() }
    }

    fun saveAccessToken(accessTokenEntity: AccessTokenEntity) =
        accessTokenDao.saveToken(accessTokenEntity = accessTokenEntity)

}
