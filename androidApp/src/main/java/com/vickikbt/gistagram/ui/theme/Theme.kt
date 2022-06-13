package com.vickikbt.gistagram.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ColorPrimaryDark,
    primaryVariant = ColorPrimaryDark,
    secondary = ColorSecondaryDark,
    surface = Color.Black,
    onSurface = Color.White,
    background = ColorPrimaryDark
)

private val LightColorPalette = lightColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimary,
    secondary = ColorSecondary,
    surface = Color.White,
    onSurface = Color.Black,
    background = ColorPrimary
)

@Composable
fun GistagramTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
