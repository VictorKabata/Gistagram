package ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.presentation.SharedAuthViewModel
import koin

@Composable
fun AuthScreen(viewModel: SharedAuthViewModel = koin.get()) {

    Box(modifier = Modifier.fillMaxWidth()) {
        val icon = if (MaterialTheme.colors.isLight) painterResource("ic_logo.png")
        else painterResource("ic_logo_dark.png")

        Icon(
            modifier = Modifier.size(200.dp).align(Alignment.Center),
            painter = icon,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = ""
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Powered By",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

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
