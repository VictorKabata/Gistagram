package ui.screens.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.ThumbUp
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
import com.vickikbt.shared.presentation.UiState
import koin
import org.jetbrains.skiko.currentSystemTheme
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

                Column(modifier = Modifier.fillMaxSize().weight(3f)) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        item {
                            StatSection(
                                user = viewer,
                                navController = navController,
                                viewModel = viewModel
                            )
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
private fun StatSection(
    navController: NavController,
    user: LoggedInUserProfileQuery.Viewer?,
    viewModel: ProfileViewModel
) {

    val userProfilePainter by remember {
        mutableStateOf(loadImageBitmap(url = user?.avatarUrl?.toString() ?: ""))
    }

    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)) {
        ItemCircleImage(
            modifier = Modifier.size(150.dp).align(Alignment.TopStart),
            image = userProfilePainter,
            contentDescription = "Profile Picture"
        ) {
            // user?.login?.let { navController.navigate("status/$it", null) } ToDo: Navigate to user profile status
        }

        BioSection(
            modifier = Modifier.wrapContentWidth().align(Alignment.TopCenter),
            user = user,
            viewModel = viewModel
        )
    }
}

@Composable
fun BioSection(
    modifier: Modifier,
    user: LoggedInUserProfileQuery.Viewer?,
    viewModel: ProfileViewModel
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    val currentTheme = viewModel.appTheme.collectAsState().value

    val themeIcon =
        if (currentTheme == 0) painterResource("ic_light.png") else painterResource("ic_dark.png")

    println("Current system theme name: ${currentSystemTheme.name}")
    println("Current system theme ordinal: ${currentSystemTheme.ordinal}")
    println("Multiplatform theme: $currentTheme")

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

            /*IconButton(
                modifier = Modifier.size(24.dp).background(MaterialTheme.colors.surface),
                onClick = {
                    // ToDo:Navigate to settings
                }
            ) {
                Icon(
                    painter = if (MaterialTheme.colors.isLight) painterResource("ic_settings.png")
                    else painterResource("ic_settings_dark.png"),
                    contentDescription = "Settings",
                    tint = MaterialTheme.colors.onSurface
                )
            }*/

            FloatingActionButton(
                modifier = Modifier.size(36.dp),
                onClick = {
                    val theme = if (currentTheme == 0) 1 else 0
                    viewModel.setAppTheme(theme = theme)
                },
                backgroundColor = MaterialTheme.colors.onSurface,
                contentColor = MaterialTheme.colors.surface
            ) {
                Icon(painter = themeIcon, contentDescription = "Theme")
            }

        }
        //endregion

        Spacer(modifier = Modifier.height(16.dp))

        //region Profile Stats
        ProfileStats(user = user)
        //endregion

        Spacer(modifier = Modifier.height(16.dp))

        //region Username
        Text(
            text = user?.name ?: "Username",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        //endregion

        Spacer(modifier = Modifier.height(10.dp))

        //region Bio
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = user?.bio ?: "Bio",
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
            letterSpacing = letterSpacing
        )
        //endregion

        Spacer(modifier = Modifier.height(6.dp))

        //region Location and Company
        Row(
            modifier = Modifier.padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ItemBioText(
                image = Icons.Rounded.LocationOn,
                text = user?.location ?: "Location"
            )
            Spacer(modifier = Modifier.width(5.dp))
            ItemBioText(
                image = Icons.Rounded.LocationOn,
                text = user?.company ?: "Company"
            )
        }
        //endregion

        Spacer(modifier = Modifier.height(6.dp))

        //region Personal Website
        ItemBioText(
            textColor = MaterialTheme.colors.secondaryVariant,
            image = Icons.Rounded.Info,
            text = user?.websiteUrl?.toString() ?: "Website"
        )
        //endregion

        Spacer(modifier = Modifier.height(6.dp))

        //region Twitter Username
        ItemBioText(
            image = Icons.Rounded.ThumbUp,
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
                modifier = Modifier.padding(horizontal = 8.dp),
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

    ProfileTabRow(
        modifier = modifier,
        onTabSelected = { selectedTabIndex = it })

    when (selectedTabIndex) {
        0 -> RepositoriesTab(repos = repos?.asReversed())
    }
}
