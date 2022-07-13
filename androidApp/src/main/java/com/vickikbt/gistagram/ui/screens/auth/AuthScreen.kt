package com.vickikbt.gistagram.ui.screens.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.ui.navigation.NavigationItem
import com.vickikbt.gistagram.utils.findActivity
import com.vickikbt.shared.domain.utils.Configs
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.presentation.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthScreen(navController: NavController, viewModel: AuthViewModel = getViewModel()) {

    val context = LocalContext.current

    val authUiState = viewModel.accessToken.collectAsState().value
    var isLoading by remember { mutableStateOf(false) }

    when (authUiState) {
        is UiState.Error -> {
            //ToDo: Display error message in snackbar
        }
        is UiState.Loading -> {
            isLoading = true
        }
        is UiState.Success -> {
            navController.navigate(NavigationItem.Profile.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_logo),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    githubOAuth(context = context)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.onSurface,
                    contentColor = MaterialTheme.colors.surface
                ),
                contentPadding = PaddingValues(horizontal = 96.dp, vertical = 8.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colors.surface)
                } else {
                    Text(
                        text = "LOGIN",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
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

    DisposableEffect(key1 = viewModel) {
        onResume(context = context, viewModel = viewModel)
        onDispose { /*ToDo*/ }
    }

}

private fun githubOAuth(context: Context) {
    val webUrl =
        "${Constants.OAUTH_BASE_URL}?client_id=${Configs.CLIENT_ID}&scope=repo,user,project,read:org&redirect_uri=${Configs.REDIRECT_URI}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
    context.startActivity(intent)
}

fun onResume(context: Context, viewModel: AuthViewModel) {
    val uri = context.findActivity()?.intent?.data

    if (uri != null && uri.toString().contains(Configs.REDIRECT_URI)) {
        val code = uri.getQueryParameter("code")

        if (code != null) {
            viewModel.fetchAccessToken(code = code)
        } else uri.getQueryParameter("error")?.let {
            Log.e("TAG", "ERROR: $it")
        }
    } else {
        Log.e("TAG", "Nothing was returned")
    }
}

