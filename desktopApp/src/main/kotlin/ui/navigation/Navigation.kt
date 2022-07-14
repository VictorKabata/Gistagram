package ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import ui.screens.auth.AuthScreen
import ui.screens.home.HomeScreen
import ui.screens.profile.ProfileScreen

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavController) {

    NavHost(navController = navController) {
        composable(route = NavigationItem.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = NavigationItem.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }.build()
}
