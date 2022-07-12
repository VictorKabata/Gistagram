package ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.presentation.UiState
import koin
import ui.navigation.NavController
import ui.navigation.NavigationItem

@Composable
fun AuthScreen(navController: NavController, viewModel: AuthViewModel = koin.get()) {

    val authUiState = viewModel.accessToken.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    when (authUiState.value) {
        is UiState.Error -> {
            println("Error!!!")
            //ToDo: Display error message in snack bar
        }
        is UiState.Loading -> {
            isLoading = true
        }
        is UiState.Success -> {
            navController.navigate(NavigationItem.Profile.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val icon = if (MaterialTheme.colors.isLight) painterResource("ic_logo.png")
        else painterResource("ic_logo_dark.png")

        Icon(
            modifier = Modifier.size(200.dp).align(Alignment.Center),
            painter = icon,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = ""
        )

        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { viewModel.fetchOAuthCode() },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colors.surface)
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 96.dp),
                        text = "LOGIN",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colors.surface,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Powered By",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "GitHub API",
                fontWeight = FontWeight.Black,
                fontSize = 24.sp,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}
