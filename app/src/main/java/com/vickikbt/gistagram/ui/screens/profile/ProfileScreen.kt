package com.vickikbt.gistagram.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.UserProfileQuery
import com.vickikbt.gistagram.ui.components.ItemCircleImage
import com.vickikbt.gistagram.ui.components.profile.ProfileAppBar
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(
    navController: NavController? = null,
    viewModel: ProfileViewModel = getViewModel()
) {

    //viewModel.getLoggedInUserProfile()

    val userProfileResult = viewModel.userProfile.observeAsState().value
    val user = userProfileResult?.data?.data?.user

    Column(modifier = Modifier.fillMaxSize()) {
        ProfileAppBar(
            title = user?.login ?: stringResource(id = R.string.title_profile),
            onSettingsClicked = {
                //ToDo: Navigate to settings
            }
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                ProfileSection(userData = user)
            }
        }
    }

}

@Composable
private fun ProfileSection(userData: UserProfileQuery.User?) {
    val userProfilePainter = rememberImagePainter(data = userData?.avatarUrl) {
        placeholder(R.drawable.ic_logo)
        crossfade(true)
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        ItemCircleImage(
            modifier = Modifier
                .size(100.dp)
                .weight(3f),
            image = userProfilePainter,
            contentDescription = stringResource(R.string.profile_picture)
        )
    }
}

@Preview
@Composable
fun Preview() {
    ProfileScreen()
}