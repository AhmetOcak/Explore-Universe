package com.spaceapp.core.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
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
            Text(text = contentText, style = MaterialTheme.typography.labelMedium)
        },
        shape = shape,
        enabled = enabled,
    )
}