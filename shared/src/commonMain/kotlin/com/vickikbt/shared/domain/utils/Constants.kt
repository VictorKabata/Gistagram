package com.vickikbt.shared.domain.utils

object Constants {

    const val REST_BASE_URL="https://api.github.com"
    const val GRAPHQL_BASE_URL = "https://api.github.com/graphql"

    const val OAUTH_BASE_URL = "https://github.com/login/oauth/authorize"
    const val TOKEN_BASE_URL = "https://github.com/login/oauth/access_token"

    const val CLIENT_ID = "636476aaacea074a6883"
    const val CLIENT_SECRET = "7fa30a9d9f40f15b1001f55c7457db419f8125b1" // ToDo: Hide this
    const val REDIRECT_URI = "vickikbt://callback"
    const val WEB_URL =
        "$OAUTH_BASE_URL?client_id=$CLIENT_ID&scope=repo,user,project,read:org&redirect_uri=$REDIRECT_URI"

    const val KEY_THEME = "Theme"
    const val KEY_LANGUAGE = "Language"
    const val KEY_IMAGE_QUALITY = "Image Quality"

    const val SOURCE_CODE_URL = "https://github.com/VictorKabata/Notflix"

    const val BUG_REPORT_EMAIL = "victorbro14@gmail.com"
    const val BUG_REPORT_SUBJECT = "Notflix-Bug report or feature request"
}
