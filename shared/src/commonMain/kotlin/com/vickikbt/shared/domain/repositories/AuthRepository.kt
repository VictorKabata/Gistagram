package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    /**
     * Return a token data class from the
     * GitHub RESTful API
     */
    suspend fun fetchToken(): Token

    /**
     * Returns a flow of token data class
     * cached in sqlDelight
     */
    suspend fun getToken(): Flow<Token?>

}
