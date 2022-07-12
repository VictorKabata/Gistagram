package com.vickikbt.gistagram.ui.screens.settings

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vickikbt.gistagram.R
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = getViewModel()) {

    val appTheme = viewModel.appTheme.collectAsState().value

    var numberCount by remember { mutableStateOf(0) }

    Log.e("Android App", "App theme: $appTheme")

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = appTheme ?: "No value set",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Button(
            onClick = {
                numberCount += 1
                viewModel.setAppTheme(theme = numberCount.toString())
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            contentPadding = PaddingValues(vertical = 8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onSurface,
                contentColor = MaterialTheme.colors.surface
            )
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.edit_profile),
                fontSize = 12.sp,
                style = MaterialTheme.typography.h4
            )
        }
    }

}
