package com.vickikbt.gistagram.repository.profile_repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.vickikbt.gistagram.UserProfileQuery

class ProfileRepositoryImpl constructor(private val apolloClient: ApolloClient):ProfileRepository  {

    
    override suspend fun getLoggedInUserProfile(): Response<UserProfileQuery.Data> {
        TODO("Not yet implemented")
    }

}