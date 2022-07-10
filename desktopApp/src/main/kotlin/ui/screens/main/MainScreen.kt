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
import ui.screens.auth.AuthScreen
import ui.screens.profile.ProfileScreen
import ui.theme.GistagramTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope, viewModel: MainViewModel = koin.get()) {

    val accessToken by remember { mutableStateOf(viewModel.accessToken.value) }
    println("Access Token: $accessToken")

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
                if (!accessToken?.accessToken.isNullOrEmpty()) {
                    ProfileScreen()
                } else {
                    AuthScreen()
                }
            }
        }
    }
}
