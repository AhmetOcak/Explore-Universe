package com.spaceapp.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Mirage,
    primaryVariant = White,
    secondary = BlueHaze,
    surface = TransparentKimberly,
    onSurface = White,
    onPrimary = White,
    onBackground = White500
)

private val LightColorPalette = lightColors(
    primary = Mirage,
    primaryVariant = White,
    secondary = BlueHaze,
    surface = TransparentKimberly,
    onSurface = White,
    onPrimary = White,
    onBackground = White500
)

@Composable
fun SpaceAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}