package com.vickikbt.gistagram.ui.screens.status

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
import com.vickikbt.gistagram.UserStatusQuery
import com.vickikbt.gistagram.ui.components.BottomNavStatus
import com.vickikbt.gistagram.ui.components.StatusToolbar
import com.vickikbt.gistagram.utils.MarkDownComposable
import com.vickikbt.gistagram.utils.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun UserStatusScreen(userLogin: String, viewModel: StatusViewModel = getViewModel()) {

    val userStatusUiState = viewModel.userStatus.observeAsState().value

    when (userStatusUiState) {
        is UiState.Error -> {
            // ToDo: Display error state
        }
        is UiState.Success -> {

            Scaffold(
                topBar = { StatusToolbar(modifier = Modifier.height(56.dp)) },
                bottomBar = { BottomNavStatus() }
            ) { it ->
                UserStatus(
                    //modifier = Modifier.align(Alignment.Center),
                    userStatus = userStatusUiState.data?.data
                )
            }

            /*Box(modifier = Modifier.fillMaxSize()) {
                StatusToolbar(
                    modifier = Modifier
                        .height(56.dp)
                        .align(Alignment.TopCenter)
                )

                UserStatus(
                    modifier = Modifier.align(Alignment.Center),
                    userStatus = userStatusUiState.data?.data
                )

                BottomNavStatus(modifier = Modifier.align(Alignment.BottomCenter))
            }*/
        }
        else -> {
            // ToDo: Display loading state
        }
    }

}

@Composable
private fun UserStatus(modifier: Modifier = Modifier, userStatus: UserStatusQuery.Data?) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        MarkDownComposable(
            url = "https://raw.githubusercontent.com/VictorKabata/VictorKabata/master/README.md",
            modifier = Modifier.fillMaxSize()
        )
    }
}
