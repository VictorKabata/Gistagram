package com.vickikbt.gistagram.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vickikbt.gistagram.R

@Composable
fun AuthScreen(navController: NavController) {

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
                    //ToDo: Request URL
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 96.dp),
                    text = "LOGIN",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colors.surface,
                    textAlign = TextAlign.Center
                )
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

}

private fun githubOAuth(){

}
