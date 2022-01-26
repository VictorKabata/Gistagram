package com.vickikbt.gistagram.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vickikbt.gistagram.R

sealed class NavigationItem(val route: String, @StringRes title: Int, @DrawableRes icon: Int?) {
    object Auth : NavigationItem("auth", R.string.app_name, null)

    object Home : NavigationItem("home", R.string.app_name, null)
    object Search : NavigationItem("search", R.string.app_name, null)
    object Notifications : NavigationItem("notifications", R.string.app_name, null)
    object Profile : NavigationItem("profile", R.string.app_name, null)
    object Settings : NavigationItem("settings", R.string.app_name, null)

    object Issues : NavigationItem("issues", R.string.app_name, null)
    object Mentions : NavigationItem("mentions", R.string.app_name, null)
}
