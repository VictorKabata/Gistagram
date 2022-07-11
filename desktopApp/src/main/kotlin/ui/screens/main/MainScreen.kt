package ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import koin
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

    println("Current destination: ${navController.currentDestination.value}")

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Gistagram",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 800.dp, // or Dp.Unspecified,
            height = 640.dp, // or Dp.Unspecified,
        )
    ) {
        GistagramTheme {
            Surface(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
                Navigation(navController = navController)
            }
        }
    }
}
