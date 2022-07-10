package com.vickikbt.shared.domain.utils

object Constants {

    const val BASE_URL = "https://api.github.com/graphql"
    const val OAUTH_BASE_URL = "https://github.com/login/oauth/authorize"
    const val TOKEN_BASE_URL = "https://github.com/login/oauth/access_token"

    const val CLIENT_ID = "636476aaacea074a6883"
    const val CLIENT_SECRET = "7fa30a9d9f40f15b1001f55c7457db419f8125b1" // ToDo: Hide this
    const val REDIRECT_URI = "vickikbt://callback"
    const val WEB_URL = "$OAUTH_BASE_URL?client_id=$CLIENT_ID&scope=repo,user,project,read:org&redirect_uri=$REDIRECT_URI"
}
