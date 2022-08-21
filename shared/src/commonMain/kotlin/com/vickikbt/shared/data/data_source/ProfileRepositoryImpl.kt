package com.vickikbt.shared.data.data_source

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.UserStatusQuery
import com.vickikbt.shared.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl constructor(private val apolloClient: ApolloClient) :
    ProfileRepository {

    override suspend fun fetchLoggedInUserProfile(): Flow<ApolloResponse<LoggedInUserProfileQuery.Data>> {

        println("Fetching logged in user")

        val loggedInUserQuery = apolloClient.query(
            LoggedInUserProfileQuery(
                organizationsCount = 2,
                pinnedReposCount = 6,
                languagesCount = 4,
                reposCount = 10,
                isFork = false
            )
        )

        return loggedInUserQuery.toFlow()
    }

    override suspend fun fetchUserStatus(userLogin: String): Flow<ApolloResponse<UserStatusQuery.Data>> {
        val userStatusQuery = apolloClient.query(UserStatusQuery(userLogin = userLogin))

        return userStatusQuery.toFlow()
    }
}
