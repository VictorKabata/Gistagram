package com.vickikbt.shared.data.data_source

import com.vickikbt.shared.data.cache.realm.dao.UserDao
import com.vickikbt.shared.data.cache.realm.models.UserEntity
import com.vickikbt.shared.data.cache.sqldelight.AccessTokenEntity
import com.vickikbt.shared.data.cache.sqldelight.dao.AccessTokenDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.domain.models.AccessToken
import com.vickikbt.shared.domain.models.User
import com.vickikbt.shared.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val tokenDao: AccessTokenDao,
    private val userDao: UserDao
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
        val responseEntity = response?.toEntity()

        responseEntity?.let {
            saveUser(userEntity = it)
        }

        return response?.toDomain()
    }

    suspend fun saveUser(userEntity: UserEntity) {
        Napier.e("Auth repository: Saving: $userEntity")

        userDao.saveUser(userEntity = userEntity)
    }

    override suspend fun getUser(): User? {
        return userDao.user?.toDomain()
    }

}
