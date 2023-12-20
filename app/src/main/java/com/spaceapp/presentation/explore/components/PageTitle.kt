package com.spaceapp.presentation.explore.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spaceapp.presentation.utils.ExploreScreenConstants

@Composable
fun PageTitle(modifier: Modifier) {
    Text(
        modifier = modifier.padding(start = 16.dp),
        text = ExploreScreenConstants.title_1,
        style = MaterialTheme.typography.headlineLarge
    )
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        thickness = 1.dp
    )
}