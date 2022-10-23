package com.spaceapp.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                    color = MaterialTheme.colors.secondary,
                    style = MaterialTheme.typography.h4
                )
                Divider(
                    modifier = modifier.width(32.dp).padding(top = 4.dp),
                    color = MaterialTheme.colors.secondary,
                    thickness = 2.dp,
                )
            }
        } else {
            Text(
                text = category,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h4
            )
        }
    }
}