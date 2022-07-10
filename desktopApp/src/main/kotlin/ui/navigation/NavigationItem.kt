package ui.navigation

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: Int? = null,
    val profilePicture: String? = null
) {
    object Auth : NavigationItem("auth", "Auth", null)
    object Profile : NavigationItem("profile", "Profile", null)
    object Settings : NavigationItem("settings", "Settings", null)
}
