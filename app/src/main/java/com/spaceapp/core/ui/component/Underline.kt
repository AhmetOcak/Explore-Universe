package com.spaceapp.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Underline(
    thickness: Dp = 2.dp,
    paddingValues: PaddingValues = PaddingValues(top = 4.dp),
    width: Dp = 32.dp,
    color: Color = MaterialTheme.colors.secondary,
) {
    Divider(
        modifier = Modifier
            .width(width)
            .padding(paddingValues),
        color = color,
        thickness = thickness,
    )
}