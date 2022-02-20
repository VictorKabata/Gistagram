package com.vickikbt.gistagram.ui.screens.profile

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun ProfileScreen(
    navController: NavController? = null,
    viewModel: ProfileViewModel = getViewModel()
) {

    /*val userProfileResult = viewModel.userProfile.observeAsState().value
    val user = userProfileResult?.data?.data?.user()

    Column(modifier = Modifier.fillMaxSize()) {
        ProfileAppBar(
            title = user?.login() ?: stringResource(id = R.string.title_profile),
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

            item {
                Spacer(modifier = Modifier.height(18.dp))
                //PinnedRepoSection(pinnedRepo = user?.pinnedItems()?.nodes())
            }

            item {
                Spacer(modifier = Modifier.height(14.dp))
                RepositoriesSection(repos = user?.repositories()?.nodes())
            }
        }
    }*/
}

/*@Composable
private fun StatSection(user: UserProfileQuery.User?) {
    val userProfilePainter = rememberImagePainter(data = user?.avatarUrl()) {
        placeholder(R.drawable.ic_logo)
        crossfade(true)
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
                .weight(3f),
            image = userProfilePainter,
            contentDescription = stringResource(R.string.profile_picture)
        )

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
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        //region Username
        Text(
            text = user?.name() ?: stringResource(R.string.username),
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
            text = user?.bio() ?: stringResource(R.string.bio),
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
                imageRes = R.drawable.ic_location,
                text = user?.location() ?: stringResource(R.string.location)
            )
            Spacer(modifier = Modifier.width(5.dp))
            ItemBioText(
                imageRes = R.drawable.ic_orgainization,
                text = user?.company() ?: stringResource(R.string.company)
            )
        }
        //endregion

        //region Personal Website
        ItemBioText(
            textColor = colorResource(id = R.color.link_color),
            imageRes = R.drawable.ic_website,
            text = user?.websiteUrl()?.toString() ?: stringResource(R.string.website)
        )
        //endregion

        //region Twitter Username
        ItemBioText(
            imageRes = R.drawable.ic_twitter,
            text = user?.twitterUsername() ?: stringResource(R.string.twitter_username)
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
                    text = stringResource(R.string.edit_profile),
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
                    painter = painterResource(id = R.drawable.ic_add_user),
                    contentDescription = stringResource(R.string.add_user),
                    modifier = Modifier.size(18.dp)
                )
            }

        }

    }
}

@Composable
fun PinnedRepoSection(pinnedRepo: List<UserProfileQuery.Node2?>?) {
    val pinnedRepoList = mutableListOf<UserProfileQuery.AsRepository?>()
    //pinnedRepo?.forEach { pinnedRepoList.add(it?.asRepository) }

    LazyRow(modifier = Modifier) {
        items(items = pinnedRepoList) { repo ->
            ItemPinnedRepo(
                modifier = Modifier.padding(horizontal = 6.dp),
                onItemClicked = {},
                pinnedRepo = repo
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun RepositoriesSection(
    modifier: Modifier = Modifier,
    repos: List<UserProfileQuery.Node3?>?
) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    ProfileTabRow(modifier = Modifier, onTabSelected = { selectedTabIndex = it })

    when (selectedTabIndex) {
        0 -> RepositoriesTab(repos = repos)
    }

}*/

@ExperimentalMaterialApi
@Preview
@Composable
fun Preview() {
    ProfileScreen()
}
