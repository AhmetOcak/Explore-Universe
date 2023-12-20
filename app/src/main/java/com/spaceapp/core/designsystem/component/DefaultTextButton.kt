package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spaceapp.core.designsystem.theme.BlueHaze

@Composable
fun DefaultTextButton(
    modifier: Modifier = Modifier,
    category: String,
    index: Int,
    selected: Int,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        if (selected == index) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.displayMedium
                )
                Divider(
                    modifier = modifier.width(32.dp).padding(top = 4.dp),
                    thickness = 2.dp,
                )
            }
        } else {
            Text(
                text = category,
                color = BlueHaze,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}