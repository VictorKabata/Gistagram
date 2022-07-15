package ui.navigation

import androidx.compose.ui.graphics.painter.Painter

sealed class NavigationItem(
    val route: String,
    val title: String,
    var icon: Painter? = null,
    val profilePicture: String? = null
) {
    object Auth : NavigationItem("auth", "Auth", null)

    object Home : NavigationItem("home", "Home", null)
    object Notifications : NavigationItem("notifications", "Notifications", null)
    object Create : NavigationItem("create", "Create Issue", null)
    object Explore : NavigationItem("explore", "Explore", null)
    object Mentions : NavigationItem("mentions", "Mentions", null)
    object Profile : NavigationItem("profile", "Profile", null)

    object Settings : NavigationItem("settings", "Settings", null)
}
