package com.vickikbt.gistagram.repository.profile_repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.vickikbt.gistagram.UserProfileQuery

class ProfileRepositoryImpl constructor(private val apolloClient: ApolloClient) :
    ProfileRepository {

    override suspend fun getLoggedInUserProfile(login: String): Response<UserProfileQuery.Data> {
        return apolloClient.query(
            UserProfileQuery(
                login = login,
                organizationsCount = Input.optional(3),
                pinnedReposCount = Input.optional(6),
                languagesCount = Input.optional(6),
                reposCount = Input.optional(10),
                isFork = Input.optional(false)
            )
        ).await()
    }

}