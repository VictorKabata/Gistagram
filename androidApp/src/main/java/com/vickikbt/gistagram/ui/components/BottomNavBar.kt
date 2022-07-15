package com.vickikbt.gistagram.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.ui.navigation.NavigationItem

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    backStackEntryState: State<NavBackStackEntry?>,
    navController: NavController,
    bottomNavItems: List<NavigationItem>
) {

    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        cutoutShape = RoundedCornerShape(70),
        elevation = 16.dp
    ) {

        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
            contentColor = MaterialTheme.colors.onSurface
        ) {
            bottomNavItems.forEach { item ->
                val isSelected = item.route == backStackEntryState.value?.destination?.route

                val bottomNavPainter = if (item.icon == null) {
                    rememberImagePainter(
                        data = item.profilePicture,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.ic_profile)
                            transformations(CircleCropTransformation())
                        }
                    )
                } else {
                    painterResource(id = item.icon)
                }

                BottomNavigationItem(
                    modifier = Modifier,
                    icon = {
                        if (item.icon == null) {
                            Image(
                                modifier = Modifier.size(24.dp),
                                painter = bottomNavPainter,
                                contentDescription = stringResource(id = item.title)
                            )
                        } else {
                            Icon(
                                painter = bottomNavPainter,
                                contentDescription = stringResource(id = item.title)
                            )
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.onSurface,
                    alwaysShowLabel = false,
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route = route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
