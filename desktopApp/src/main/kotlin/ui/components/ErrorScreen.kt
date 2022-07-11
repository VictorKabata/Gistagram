package com.vickikbt.gistagram.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vickikbt.gistagram.R

@Composable
fun ErrorScreen(navController: NavController, isRetriable: Boolean = true, action: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isRetriable) {
            Button(
                onClick = {
                    action.invoke()
                    navController.navigateUp()
                },
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSurface),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 32.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.retry),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}
