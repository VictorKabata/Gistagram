package com.vickikbt.gistagram.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vickikbt.gistagram.ui.components.BottomNavBar
import com.vickikbt.gistagram.ui.navigation.Navigation
import com.vickikbt.gistagram.ui.navigation.NavigationItem
import com.vickikbt.gistagram.ui.theme.GistagramTheme
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val mainViewModel: MainViewModel = getViewModel()
            val theme = mainViewModel.appTheme.collectAsState()

            GistagramTheme(darkTheme = theme.value == 1) {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(viewModel: MainViewModel = getViewModel()) {

    val user = viewModel.user.collectAsState().value

    Napier.e("User cached in realm: $user")

    val navController = rememberAnimatedNavController()

    val topLevelDestinations = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Notifications,
        NavigationItem.Profile.apply { this.profilePicture = user?.avatarUrl }
    )

    val isTopLevelDestination =
        navController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

    val backStackEntryState = navController.currentBackStackEntryAsState()

    val accessToken by remember { mutableStateOf(viewModel.accessToken.value) }

    Scaffold(bottomBar = {
        if (isTopLevelDestination) {
            BottomNavBar(
                navController = navController,
                backStackEntryState = backStackEntryState,
                bottomNavItems = topLevelDestinations
            )
        }
    }) {
        Navigation(
            modifier = Modifier.padding(it),
            navController = navController,
            isLoggedIn = !accessToken?.accessToken.isNullOrEmpty()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GistagramTheme {
    }
}
