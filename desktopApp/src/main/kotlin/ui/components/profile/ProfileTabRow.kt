package ui.components.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.theme.Inactive

@Composable
fun ProfileTabRow(
    modifier: Modifier = Modifier,
    onTabSelected: (selectedIndex: Int) -> Unit
) {

    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabItems = listOf(TabRowItem.Repos, TabRowItem.Forks, TabRowItem.Gists)

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        contentColor = MaterialTheme.colors.background,
        backgroundColor = Color.Transparent
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

                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp),
                    painter = painterResource(tabRowItem.icon),
                    contentDescription = "Title",
                    tint = if (selectedTabIndex == index) MaterialTheme.colors.onBackground else Inactive
                )
            }
        }
    }
}

sealed class TabRowItem(val icon: String, val title: String) {
    object Repos : TabRowItem("ic_logo_dark", "Repositories")
    object Forks : TabRowItem("ic_logo_dark", "Forks")
    object Gists : TabRowItem("ic_logo_dark", "Gists")
}
