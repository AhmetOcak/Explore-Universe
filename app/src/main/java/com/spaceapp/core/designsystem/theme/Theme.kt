package com.spaceapp.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val colorPalette = lightColorScheme(
    primary = Mirage,
    secondary = BlueHaze,
    surface = TransparentKimberly,
    onSurface = White,
    onPrimary = White,
    onBackground = White
)

@Composable
fun SpaceAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorPalette,
        typography = Typography,
        content = content
    )
}