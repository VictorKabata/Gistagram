package com.vickikbt.gistagram.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vickikbt.gistagram.R

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?,
    var profilePicture: String? = null
) {
    object Auth : NavigationItem("login", R.string.title_login, null)

    object Home : NavigationItem("home", R.string.title_home, R.drawable.ic_home)
    object Search : NavigationItem("search", R.string.title_search, R.drawable.ic_search)
    object Notifications :
        NavigationItem("notifications", R.string.title_notifications, R.drawable.ic_heart)

    object Profile : NavigationItem(
        "profile",
        R.string.title_profile,
        null,
        null
    )

    object Settings : NavigationItem("settings", R.string.title_settings, R.drawable.ic_settings)

    object UserStatus : NavigationItem("status/{userLogin}", R.string.title_user_status, null)

    object RepoStatus :
        NavigationItem("status/{userLogin}/{repoName}", R.string.title_repo_status, null)

    object Issues : NavigationItem("issues", R.string.title_issues, null)
    object Mentions : NavigationItem("mentions", R.string.title_mentions, null)

    object Error : NavigationItem("error", R.string.title_error, null)
}
