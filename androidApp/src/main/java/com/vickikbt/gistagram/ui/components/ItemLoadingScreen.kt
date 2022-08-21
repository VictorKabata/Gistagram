package com.vickikbt.gistagram.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ItemLoadingScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
