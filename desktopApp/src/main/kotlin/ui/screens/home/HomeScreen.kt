package ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import koin
import ui.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = koin.get()) {

    val user = viewModel.user.collectAsState().value

    println("User cached in realm: $user")

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = user.toString(),
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onSurface
    )


}
