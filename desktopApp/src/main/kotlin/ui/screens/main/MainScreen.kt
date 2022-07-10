package ui.screens.main

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

@Composable
fun MainScreen(applicationScope: ApplicationScope, viewModel: MainViewModel = koin.get()) {

    val accessToken by remember { mutableStateOf(viewModel.accessToken.value) }
    println("Access Token: $accessToken")

    val navController by rememberNavController(
        startDestination = if (accessToken != null) NavigationItem.Profile.route else NavigationItem.Auth.route
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
            Surface {
                Navigation(navController = navController)
            }
        }
    }
}
