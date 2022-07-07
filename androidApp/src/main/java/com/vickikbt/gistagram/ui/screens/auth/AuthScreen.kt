package com.vickikbt.gistagram.ui.screens.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.vickikbt.gistagram.utils.UiState
import com.vickikbt.gistagram.utils.findActivity
import com.vickikbt.shared.domain.utils.Constants
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthScreen(navController: NavController, viewModel: AuthViewModel = getViewModel()) {

    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    when (val authUiState = viewModel.authUiState.observeAsState().value) {
        is UiState.Error -> {
            isLoading = false
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()

            Log.e("TAG", "Error: ${authUiState.error}")
        }
        is UiState.Success -> {
            isLoading = false

            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()

            Log.e("TAG", "Token: ${authUiState.data}")
            // navController.navigate(NavigationItem.Profile.route)
        }
        is UiState.Loading -> {
            isLoading = true
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {

        Icon(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            tint = MaterialTheme.colors.onSurface
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
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
            ) {
                if (isLoading == true) {
                    CircularProgressIndicator(color = MaterialTheme.colors.surface)
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 88.dp),
                        text = "LOGIN",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Powered By",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = "GitHub API",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    DisposableEffect(key1 = viewModel) {
        onResume(context = context, viewModel = viewModel)
        onDispose { /*ToDo*/ }
    }
}

private fun githubOAuth(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.OAUTH_FULL_URL))
    context.startActivity(intent)
}

fun onResume(context: Context, viewModel: AuthViewModel) {
    val uri = context.findActivity()?.intent?.data

    if (uri != null && uri.toString().contains(Constants.REDIRECT_URL)) {
        val code = uri.getQueryParameter("code")
        Log.e("TAG", "URI: $uri")

        if (code != null) {
            Log.e("TAG", "Code: $code")
            viewModel.fetchAccessToken(code = code)
        } else uri.getQueryParameter("error")?.let {
            Log.e("TAG", "Error: $it")
        }
    } else {
        Napier.e("Nothing was returned")
    }
}
