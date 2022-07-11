package ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.shared.domain.utils.UiState
import koin
import ui.components.ItemCircleImage
import ui.components.ItemLoadingScreen
import ui.components.profile.ItemBioText
import ui.components.profile.ItemPinnedRepo
import ui.components.profile.ProfileStats
import ui.components.profile.ProfileTabRow
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

            Row {
                Spacer(modifier = Modifier.weight(1f))

                Column(modifier = Modifier.fillMaxSize().weight(4f)) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item {
                            StatSection(user = viewer, navController = navController)
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

                Spacer(modifier = Modifier.weight(1f))
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
        mutableStateOf(loadImageBitmap(url = user?.avatarUrl?.toString() ?: ""))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.Top
    ) {
        ItemCircleImage(
            modifier = Modifier.size(140.dp),
            image = userProfilePainter,
            contentDescription = "Profile Picture"
        ) {
            // user?.login?.let { navController.navigate("status/$it", null) } ToDo: Navigate to user profile status
        }

        BioSection(modifier = Modifier, user = user)
    }
}

@Composable
fun BioSection(modifier: Modifier, user: LoggedInUserProfileQuery.Viewer?) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(modifier = modifier) {

        //region Login, Edit Profile Button, Settings Icon
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user?.login ?: "Login",
                color = MaterialTheme.colors.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraLight,
                letterSpacing = letterSpacing,
                lineHeight = lineHeight,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Button(
                onClick = { },
                border = BorderStroke(width = .5.dp, color = Color.Gray),
                contentPadding = PaddingValues(horizontal = 8.dp),
                shape = RoundedCornerShape(3.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "Edit Profile",
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            IconButton(
                modifier = Modifier.size(40.dp).background(MaterialTheme.colors.surface),
                onClick = {
                    // ToDo:Navigate to settings
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
        //endregion

        Spacer(modifier = Modifier.height(16.dp))

        //region Profile Stats
        ProfileStats(user = user)
        //endregion

        Spacer(modifier = Modifier.height(16.dp))

        //region Bio
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
