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
                login,
                Input.optional(3),
                Input.optional(6),
                Input.optional(6),
                Input.optional(10),
                Input.optional(false)
            )
        ).await()
    }

}