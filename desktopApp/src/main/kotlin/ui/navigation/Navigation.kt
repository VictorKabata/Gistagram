package ui.navigation

import androidx.compose.runtime.Composable
import ui.screens.auth.AuthScreen
import ui.screens.profile.ProfileScreen

@Composable
fun Navigation(navController: NavController) {

    NavHost(navController = navController) {
        composable(route = NavigationItem.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(route = NavigationItem.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }.build()
}
