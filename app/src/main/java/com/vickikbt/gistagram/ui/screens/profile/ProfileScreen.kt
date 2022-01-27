package com.vickikbt.gistagram.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.UserProfileQuery
import com.vickikbt.gistagram.ui.components.ItemCircleImage
import com.vickikbt.gistagram.ui.components.profile.ProfileAppBar
import com.vickikbt.gistagram.ui.components.profile.ProfileStats
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

        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                StatSection(user = user)
            }

            item {
                BioSection(user = user)
            }
        }
    }

}

@Composable
private fun StatSection(user: UserProfileQuery.User?) {
    val userProfilePainter = rememberImagePainter(data = user?.avatarUrl) {
        placeholder(R.drawable.ic_logo)
        crossfade(true)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemCircleImage(
            modifier = Modifier
                .size(100.dp)
                .weight(3f),
            image = userProfilePainter,
            contentDescription = stringResource(R.string.profile_picture)
        )

        Spacer(modifier = Modifier.width(16.dp))

        ProfileStats(modifier = Modifier.weight(7f), user = user)
    }
}

@Composable
fun BioSection(user: UserProfileQuery.User?) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = user?.name ?: stringResource(R.string.username),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = user?.bio ?: stringResource(R.string.bio),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            fontSize = 13.sp,
            letterSpacing = letterSpacing
        )

    }
}

@Preview
@Composable
fun Preview() {
    ProfileScreen()
}