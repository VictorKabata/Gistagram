package com.vickikbt.shared.data.data_source

import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.data.cache.sqldelight.dao.AccessTokenDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val tokenDao: AccessTokenDao
) : AuthRepository {

    override suspend fun fetchAccessToken(code: String): AccessToken? {
        val response = apiClient.fetchAccessToken(code = code)
        val responseEntity = response?.toEntity()

        responseEntity?.let {
            saveAccessToken(accessTokenEntity = it)
        }

        return responseEntity?.toDomain()
    }

    override suspend fun getAccessToken(): Flow<AccessToken?> {
        return tokenDao.getAccessToken.map { it?.toDomain() }
    }

    suspend fun saveAccessToken(accessTokenEntity: AccessTokenEntity) =
        tokenDao.saveAccessToken(accessTokenEntity = accessTokenEntity)

    override suspend fun fetchUserProfile(): User? {
        val accessToken = tokenDao.getAccessToken.first()?.accessToken

        val response = apiClient.fetchUserProfile(accessToken = accessToken ?: "")

        response?.let {
            // ToDo: Cache data to Realm
        }

        println("Fetched user: $response")

        return response?.toDomain()
    }

}
