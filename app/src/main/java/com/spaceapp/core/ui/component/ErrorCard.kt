package com.spaceapp.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.ui.theme.TransparentKimberly

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    errorDescription: String,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    errorIconSize: Dp = 96.dp,
    height: Dp = 250.dp
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .padding(paddingValues),
        shape = RoundedCornerShape(15),
        backgroundColor = TransparentKimberly,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(errorIconSize),
                painter = painterResource(id = R.drawable.error),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "OOPS"
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = errorDescription,
                textAlign = TextAlign.Center
            )
        }
    }
}