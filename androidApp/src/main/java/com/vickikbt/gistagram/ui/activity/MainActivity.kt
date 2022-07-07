package com.vickikbt.gistagram.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vickikbt.gistagram.ui.navigation.Navigation
import com.vickikbt.gistagram.ui.theme.GistagramTheme

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            GistagramTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

/*@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()

    val topLevelDestinations = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Notifications,
        NavigationItem.Profile
    )

    val isTopLevelDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

    val backStackEntryState = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (isTopLevelDestination) {
                BottomNavBar(
                    navController = navController,
                    backStackEntryState = backStackEntryState,
                    bottomNavItems = topLevelDestinations
                )
            }
        }
    ) { paddingValues ->
        Navigation(navController = navController)
    }
}*/

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()

    Scaffold { paddingValues ->
        Navigation(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GistagramTheme {
    }
}
