package com.spaceapp.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColorScheme(
    primary = Mirage,
    secondary = BlueHaze,
    surface = TransparentKimberly,
    onSurface = White,
    onPrimary = White,
    onBackground = White500
)

private val lightColorPalette = lightColorScheme(
    primary = Mirage,
    secondary = BlueHaze,
    surface = TransparentKimberly,
    onSurface = White,
    onPrimary = White,
    onBackground = White500
)

@Composable
fun SpaceAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}