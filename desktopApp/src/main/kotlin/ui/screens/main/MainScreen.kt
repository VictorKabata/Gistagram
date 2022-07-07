package ui.screens.main

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import ui.screens.auth.AuthScreen
import ui.theme.GistagramDesktopTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope) {

    val appIcon = if (MaterialTheme.colors.isLight) painterResource("ic_logo_dark.png")
    else painterResource("ic_logo_dark.png")

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Gistagram",
        icon = appIcon,
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 800.dp, // or Dp.Unspecified,
            height = 640.dp, // or Dp.Unspecified,
        )
    ) {
        GistagramDesktopTheme {
            Surface {
                AuthScreen()
            }
        }
    }
}
