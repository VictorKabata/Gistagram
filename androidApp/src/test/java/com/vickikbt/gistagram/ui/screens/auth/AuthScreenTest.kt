package com.vickikbt.gistagram.ui.screens.auth

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.vickikbt.gistagram.ui.theme.GistagramTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class AuthScreenTest {

    private lateinit var navController: NavController

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        ShadowLog.stream = System.out
    }

    @Test
    fun `should display login button`() {
        composeTestRule.setContent {
            GistagramTheme {
                AuthScreen(navController = navController)
            }
        }
    }

}
