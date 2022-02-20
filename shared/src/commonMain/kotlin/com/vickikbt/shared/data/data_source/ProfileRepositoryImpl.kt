package com.vickikbt.shared.data.data_source

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.shared.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl constructor(private val apolloClient: ApolloClient) : ProfileRepository {

    override suspend fun getLoggedInUserProfile(): Flow<ApolloResponse<LoggedInUserProfileQuery.Data>> {

        val loggedInUserQuery = apolloClient.query(
            LoggedInUserProfileQuery(
                organizationsCount = 10,
                pinnedReposCount = 6,
                languagesCount = 4,
                reposCount = 10,
                isFork = null
            )
        )

        return loggedInUserQuery.toFlow()
    }
}
