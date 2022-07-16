package com.vickikbt.gistagram.ui.components.profile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.ui.theme.Inactive

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
                    painter = painterResource(id = tabRowItem.icon),
                    contentDescription = stringResource(id = tabRowItem.title),
                    tint = if (selectedTabIndex == index) MaterialTheme.colors.onBackground else Inactive
                )
            }
        }
    }
}

sealed class TabRowItem(@DrawableRes val icon: Int, @StringRes val title: Int) {
    object Repos : TabRowItem(R.drawable.ic_folder, R.string.repos)
    object Forks : TabRowItem(R.drawable.ic_fork, R.string.forks)
    object Gists : TabRowItem(R.drawable.ic_file, R.string.gists)
}
