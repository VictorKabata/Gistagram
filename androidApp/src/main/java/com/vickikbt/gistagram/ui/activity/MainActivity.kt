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
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vickikbt.gistagram.ui.navigation.Navigation
import com.vickikbt.gistagram.ui.theme.GistagramTheme
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MainScreen()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(viewModel: MainViewModel = getViewModel()) {
    val navController = rememberAnimatedNavController()

    val appTheme = viewModel.appTheme.collectAsState().value
    val theme:Boolean = appTheme=="dark"

    val accessToken by remember { mutableStateOf(viewModel.accessToken.value) }

    Scaffold {
        GistagramTheme(darkTheme = theme) {
            Surface(color = MaterialTheme.colors.background) {
                Navigation(
                    navController = navController,
                    isLoggedIn = !accessToken?.accessToken.isNullOrEmpty()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GistagramTheme {
    }
}
