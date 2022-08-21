package ui.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skiko.currentSystemTheme
import ui.theme.Inactive

@Composable
fun ProfileTabRow(
    modifier: Modifier = Modifier,
    onTabSelected: (selectedIndex: Int) -> Unit
) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabItems = listOf(
        TabRowItem.Repos.apply {
            icon = if (currentSystemTheme.ordinal == 0) "ic_repo.png" else "ic_repo_dark.png"
        },
        TabRowItem.Forks.apply {
            icon = if (currentSystemTheme.ordinal == 0) "ic_fork.png" else "ic_fork_dark.png"
        },
        TabRowItem.Stars.apply {
            icon = if (MaterialTheme.colors.isLight) "ic_star.png" else "ic_star_dark.png"
        }
    )

    Row(modifier = modifier) {
        Spacer(modifier = Modifier.weight(2f))

        TabRow(
            modifier = Modifier.weight(4f),
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface,
        ) {

            tabItems.forEachIndexed { index, tabRowItem ->
                Tab(
                    selected = selectedTabIndex == index,
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    unselectedContentColor = Inactive,
                    onClick = {
                        selectedTabIndex = index
                        onTabSelected(index)
                    },
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(tabRowItem.icon),
                            contentDescription = "Title",
                            tint = if (selectedTabIndex == index) MaterialTheme.colors.onBackground else Inactive
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = tabRowItem.title,
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(2f))
    }
}

sealed class TabRowItem(var icon: String, val title: String) {
    object Repos : TabRowItem("ic_logo_dark.png", "REPOS")
    object Forks : TabRowItem("ic_logo_dark.png", "FORKS")
    object Stars : TabRowItem("ic_logo_dark.png", "STARS")
}
