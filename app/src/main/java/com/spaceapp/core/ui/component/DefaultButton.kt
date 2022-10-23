package com.spaceapp.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier,
    onClick: () -> Unit,
    contentText: String,
    shape: Shape = RoundedCornerShape(50),
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding,
        content = {
            Text(text = contentText)
        },
        shape = shape,
        enabled = enabled,
    )
}