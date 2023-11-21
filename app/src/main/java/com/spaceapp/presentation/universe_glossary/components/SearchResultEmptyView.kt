package com.spaceapp.presentation.universe_glossary.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.presentation.utils.UniverseGlossaryScreenConstants

@Composable
fun SearchResultEmpty(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier.size(72.dp),
            painter = painterResource(id = R.drawable.empty_list),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = modifier.padding(top = 16.dp),
            text = UniverseGlossaryScreenConstants.no_result,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }
}