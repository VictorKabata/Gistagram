package com.vickikbt.gistagram.repository.profile_repository

import com.apollographql.apollo.api.Response
import com.vickikbt.gistagram.UserProfileQuery

interface ProfileRepository {

    suspend fun getLoggedInUserProfile():Response<UserProfileQuery.Data>

}