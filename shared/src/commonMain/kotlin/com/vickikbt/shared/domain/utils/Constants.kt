package com.vickikbt.shared.domain.utils

object Constants {

    const val OAUTH_URL = "https://github.com/login/oauth/authorize"
    const val CLIENT_ID = "636476aaacea074a6883"
    const val CLIENT_SECRET = "bec30e0e8ceb4627fffdf4a81dd85d78671aced0" //ToDo: Hide these
    const val REDIRECT_URL = "vickikbt://callback"
    const val OAUTH_FULL_URL =
        "$OAUTH_URL?client_id=$CLIENT_ID&scope=repo&redirect_url=$REDIRECT_URL"
    const val BASE_URL = "https://api.github.com/graphql"
}
