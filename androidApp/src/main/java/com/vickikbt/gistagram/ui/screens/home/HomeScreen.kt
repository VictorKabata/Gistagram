package com.vickikbt.gistagram.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.vickikbt.shared.presentation.UiState
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    val receivedEventsUiState = viewModel.receivedFeeds.collectAsState().value

    when (receivedEventsUiState) {
        is UiState.Error -> {
            Napier.e("Error: ${receivedEventsUiState.error}")
        }
        is UiState.Loading -> {
            Napier.e("Loading received events...")
        }
        is UiState.Success -> {
            Napier.e("Received events: ${receivedEventsUiState.data}")
        }
    }

}
