package ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector? = null,
    val profilePicture: String? = null
) {
    object Auth : NavigationItem("auth", "Auth", null)

    object Home : NavigationItem("home", "Home", Icons.Rounded.Home)
    object Notifications :
        NavigationItem("notifications", "Notifications", Icons.Rounded.Notifications)

    object Explore : NavigationItem("explore", "Explore", Icons.Rounded.Search)
    object Profile : NavigationItem("profile", "Profile", Icons.Rounded.AccountCircle)

    object Settings : NavigationItem("settings", "Settings", null)
}
