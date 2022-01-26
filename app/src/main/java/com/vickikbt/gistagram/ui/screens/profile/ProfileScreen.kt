package com.vickikbt.gistagram.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.vickikbt.gistagram.utils.UiState
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = getViewModel()) {

    //viewModel.getLoggedInUserProfile()

    val userProfile = viewModel.userProfile.observeAsState().value

    when (userProfile) {
        is UiState.Error -> Timber.e("Error: ${userProfile.error}")
        is UiState.Loading -> Timber.e("Loading Results")
        is UiState.Success -> Timber.e("Success: ${userProfile.data?.data?.user}")
        null -> Timber.e("Value is null")
    }

}