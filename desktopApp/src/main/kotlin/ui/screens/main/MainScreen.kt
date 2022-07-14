package ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import koin
import ui.components.MainAppBar
import ui.components.NavigationBar
import ui.navigation.Navigation
import ui.navigation.NavigationItem
import ui.navigation.rememberNavController
import ui.theme.GistagramTheme

@ExperimentalMaterialApi
@Composable
fun MainScreen(applicationScope: ApplicationScope, viewModel: MainViewModel = koin.get()) {

    val accessToken = viewModel.accessToken.collectAsState()
    println("Access Token: ${accessToken.value}")

    val navController by rememberNavController(
        startDestination = if (accessToken.value != null) NavigationItem.Profile.route else NavigationItem.Auth.route
    )

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Gistagram",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 800.dp, // or Dp.Unspecified,
            height = 640.dp, // or Dp.Unspecified,
        )
    ) {

        val isLight = MaterialTheme.colors.isLight

        val topLevelDestinations = listOf(
            NavigationItem.Home.apply {
                icon =
                    if (isLight) painterResource("ic_home.png") else painterResource("ic_home_dark.png")
            },
            NavigationItem.Notifications.apply {
                icon =
                    if (isLight) painterResource("ic_notifications.png") else painterResource("ic_notifications_dark.png")
            },
            NavigationItem.Create.apply {
                icon =
                    if (isLight) painterResource("ic_create.png") else painterResource("ic_create_dark.png")
            },
            NavigationItem.Explore.apply {
                icon =
                    if (isLight) painterResource("ic_explore.png") else painterResource("ic_explore_dark.png")
            },
            NavigationItem.Mentions.apply {
                icon =
                    if (isLight) painterResource("ic_heart.png") else painterResource("ic_heart_dark.png")
            },
            NavigationItem.Profile.apply {
                icon =
                    if (isLight) painterResource("ic_profile.png") else painterResource("ic_profile_dark.png")
            }
        )

        val currentDestination = navController.currentDestination.value

        GistagramTheme {
            Surface(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
                Scaffold(
                    topBar = {
                        if (currentDestination in topLevelDestinations.map { it.route }) {
                            MainAppBar(
                                modifier = Modifier.padding(vertical = 8.dp),
                                onSearch = {
                                    //ToDo: Search item-Drop down UI
                                }
                            ) {
                                NavigationBar(
                                    navController = navController,
                                    navigationDestinations = topLevelDestinations
                                )
                            }
                        }
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}
