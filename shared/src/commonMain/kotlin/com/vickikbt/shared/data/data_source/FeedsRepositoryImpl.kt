package com.vickikbt.shared.data.data_source

import com.vickikbt.shared.data.cache.realm.dao.UserDao
import com.vickikbt.shared.data.cache.sqldelight.dao.AccessTokenDao
import com.vickikbt.shared.data.network.rest.ApiClient
import com.vickikbt.shared.data.network.rest.models.ReceivedEventDto
import com.vickikbt.shared.domain.repositories.FeedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class FeedsRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val accessTokenDao: AccessTokenDao,
    private val userDao: UserDao
) : FeedsRepository {

    override suspend fun fetchReceivedEvents(): Flow<List<ReceivedEventDto?>?>? {
        val userName = userDao.user?.first()?.login
        val accessToken = accessTokenDao.getAccessToken.first()?.accessToken

        return flowOf(
            apiClient.fetchReceivedFeeds(
                userName = userName ?: "",
                accessToken = accessToken ?: ""
            )
        )
    }

}
