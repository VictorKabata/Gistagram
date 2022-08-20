package com.vickikbt.gistagram.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vickikbt.gistagram.ui.screens.auth.AuthScreen
import com.vickikbt.gistagram.ui.screens.home.HomeScreen
import com.vickikbt.gistagram.ui.screens.notifications.NotificationsScreen
import com.vickikbt.gistagram.ui.screens.profile.ProfileScreen
import com.vickikbt.gistagram.ui.screens.search.SearchScreen
import com.vickikbt.gistagram.ui.screens.settings.SettingsScreen
import com.vickikbt.gistagram.ui.screens.status.RepoStatusScreen
import com.vickikbt.gistagram.ui.screens.status.UserStatusScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController, isLoggedIn: Boolean, modifier: Modifier = Modifier) {

    // val defaultEnterAnimationDuration = 600
    // val defaultExitAnimationDuration = 1100
    // val slideDefaultInitialOffset = 1800
    // val slideDefaultTargetOffset = 1500

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavigationItem.Profile.route else NavigationItem.Auth.route
    ) {
        composable(route = NavigationItem.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = NavigationItem.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(route = NavigationItem.Notifications.route) {
            NotificationsScreen(navController = navController)
        }

        composable(route = NavigationItem.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = NavigationItem.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(
            route = NavigationItem.UserStatus.route,
            arguments = listOf(
                navArgument("userLogin") {
                    type = NavType.StringType
                }
            )
        ) {
            val userLogin = it.arguments?.getString("userLogin")

            userLogin?.let { UserStatusScreen(userLogin = userLogin) }
        }

        composable(
            route = NavigationItem.RepoStatus.route,
            arguments = listOf(
                navArgument("userLogin") {
                    type = NavType.StringType
                },
                navArgument("repoName") {
                    type = NavType.StringType
                }
            )
        ) {
            val userLogin = it.arguments?.getString("userLogin")
            val repoName = it.arguments?.getString("repoName")

            userLogin?.let {
                repoName?.let {
                    RepoStatusScreen(userLogin = userLogin, repoName = repoName)
                }
            }
        }

        /*composable(route = NavigationItem.Error.route) {
            ErrorScreen(navController = navController)
        }*/
    }
}
