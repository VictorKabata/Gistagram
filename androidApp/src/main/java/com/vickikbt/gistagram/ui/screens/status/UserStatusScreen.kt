package com.vickikbt.gistagram.ui.screens.status

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.gistagram.ui.components.BottomNavStatus
import com.vickikbt.gistagram.ui.components.ItemLoadingScreen
import com.vickikbt.gistagram.ui.components.StatusToolbar
import com.vickikbt.gistagram.utils.MarkDownComposable
import com.vickikbt.shared.domain.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun UserStatusScreen(
    userLogin: String = "VictorKabata",
    viewModel: StatusViewModel = getViewModel()
) {

    when (viewModel.userStatus.observeAsState().value) {
        is UiState.Error -> {
            // ToDo: Display error state
        }
        is UiState.Success -> {

            Scaffold(
                topBar = { StatusToolbar(modifier = Modifier.height(56.dp), userName = userLogin) },
                bottomBar = { BottomNavStatus() }
            ) { it ->
                UserStatus(userLogin = userLogin, paddingValues = it)
            }
        }
        else -> {
            ItemLoadingScreen()
        }
    }

}

@Composable
private fun UserStatus(
    modifier: Modifier = Modifier,
    userLogin: String,
    paddingValues: PaddingValues
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        MarkDownComposable(
            url = "https://raw.githubusercontent.com/${userLogin}/$userLogin/master/README.md",
            modifier = Modifier.fillMaxSize()
        )
    }
}
