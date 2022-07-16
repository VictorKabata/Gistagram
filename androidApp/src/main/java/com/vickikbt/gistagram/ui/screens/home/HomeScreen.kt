package com.vickikbt.gistagram.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    val user = viewModel.user.collectAsState().value

    println("User cached in realm: $user")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            text = user.toString(),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onSurface
        )

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { viewModel.getUser() },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
        ) {
            Text(
                modifier = Modifier,
                text = "Fetch User",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.surface
            )
        }
    }

}
