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
                    //ToDo: Open browser for Oauth flow
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 88.dp),
                    text = "LOGIN",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.surface
                )
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
}
