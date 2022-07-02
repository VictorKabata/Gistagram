package com.vickikbt.shared.data.data_source

import com.vickikbt.shared.data.cache.sqldelight.dao.TokenDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.domain.models.Token
import com.vickikbt.shared.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val tokenDao: TokenDao
) : AuthRepository {

    override suspend fun fetchToken(): Token {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): Flow<Token?> {
        return tokenDao.getToken.map { it?.toDomain() }
    }

}
