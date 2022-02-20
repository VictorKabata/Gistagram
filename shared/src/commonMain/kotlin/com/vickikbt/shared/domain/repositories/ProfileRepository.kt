package com.vickikbt.shared.domain.repositories

import com.apollographql.apollo3.api.ApolloResponse
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    /**
     * Fetches currently logged in user profile and
     * returns a flow of the response(user profile)
     */
    suspend fun getLoggedInUserProfile(): Flow<ApolloResponse<LoggedInUserProfileQuery.Data>>
}
