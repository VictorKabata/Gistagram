package ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.shared.domain.utils.UiState
import koin
import ui.components.ItemCircleImage
import ui.components.ItemLoadingScreen
import ui.components.profile.*
import ui.navigation.NavController
import ui.screens.profile.tabs.RepositoriesTab
import utils.loadImageBitmap

@ExperimentalMaterialApi
@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = koin.get()) {

    val userProfileUiState = viewModel.userProfile.collectAsState()

    when (userProfileUiState.value) {
        is UiState.Error -> {
            println("Error: ${userProfileUiState.value?.error}")
        }
        is UiState.Success -> {
            val viewer = userProfileUiState.value?.data?.data?.viewer

            println("Viewer: $viewer")

            Column(modifier = Modifier.fillMaxSize()) {
                ProfileAppBar(
                    title = viewer?.login ?: "Profile",
                    onSettingsClicked = {
                        // ToDo: Navigate to settings
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item {
                        StatSection(user = viewer, navController = navController)
                    }

                    item {
                        BioSection(user = viewer)
                    }

                    item {
                        Spacer(modifier = Modifier.height(18.dp))

                        viewer.let {
                            PinnedRepoSection(
                                navController = navController,
                                user = it,
                                pinnedRepo = it?.pinnedItems?.nodes ?: listOf()
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(14.dp))
                        RepositoriesSection(repos = viewer?.repositories?.nodes)
                    }
                }
            }
        }
        is UiState.Loading -> {
            ItemLoadingScreen()
        }
    }

}

@Composable
private fun StatSection(navController: NavController, user: LoggedInUserProfileQuery.Viewer?) {

    val userProfilePainter by remember {
        mutableStateOf(
            loadImageBitmap(
                url = user?.avatarUrl?.toString() ?: ""
            )
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ItemCircleImage(
            modifier = Modifier
                .size(100.dp)
                .weight(3f)
                .border(width = 2.dp, color = Color.Gray, shape = CircleShape),
            image = userProfilePainter,
            contentDescription = "Profile Picture"
        ) {
            // user?.login?.let { navController.navigate("status/$it", null) } ToDo: Navigate to user profile status
        }

        ProfileStats(modifier = Modifier.weight(7f), user = user)
    }
}

@Composable
fun BioSection(user: LoggedInUserProfileQuery.Viewer?) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        //region Username
        Text(
            text = user?.name ?: "Username",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        //endregion

        //region Bio
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = user?.bio ?: "Bio",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            letterSpacing = letterSpacing
        )
        //endregion

        //region Location and Company
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ItemBioText(
                image = painterResource("ic_logo.png"),
                text = user?.location ?: "Location"
            )
            Spacer(modifier = Modifier.width(5.dp))
            ItemBioText(
                image = painterResource("ic_logo.png"),
                text = user?.company ?: "Company"
            )
        }
        //endregion

        //region Personal Website
        ItemBioText(
            textColor = MaterialTheme.colors.secondaryVariant,
            image = painterResource("ic_logo.png"),
            text = user?.websiteUrl?.toString() ?: "Website"
        )
        //endregion

        //region Twitter Username
        ItemBioText(
            image = painterResource("ic_logo.png"),
            text = user?.twitterUsername ?: "Twitter username"
        )
        //endregion

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { },
                modifier = Modifier.weight(8f),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSurface),
                contentPadding = PaddingValues(vertical = 8.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "Edit Profile",
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.h4
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSurface),
                contentPadding = PaddingValues(vertical = 8.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Image(
                    painter = painterResource("ic_logo.png"),
                    contentDescription = "Add User",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun PinnedRepoSection(
    navController: NavController,
    user: LoggedInUserProfileQuery.Viewer?,
    pinnedRepo: List<LoggedInUserProfileQuery.Node1?>
) {
    val pinnedRepoList = mutableListOf<LoggedInUserProfileQuery.OnRepository?>()
    pinnedRepo.forEach { pinnedRepoList.add(it?.onRepository) }

    LazyRow(modifier = Modifier) {

        items(items = pinnedRepoList) { repo ->
            ItemPinnedRepo(
                modifier = Modifier.padding(horizontal = 6.dp),
                onItemClicked = {
                    user?.login?.let { login ->
                        repo?.name?.let { repo ->
                            //navController.navigate() ToDo: Navigate to repo status
                        }
                    }
                },
                pinnedRepo = repo
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun RepositoriesSection(
    modifier: Modifier = Modifier,
    repos: List<LoggedInUserProfileQuery.Node3?>?
) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    ProfileTabRow(modifier = modifier, onTabSelected = { selectedTabIndex = it })

    when (selectedTabIndex) {
        0 -> RepositoriesTab(repos = repos?.asReversed())
    }
}
