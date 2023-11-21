package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun BackgroundImage(
    modifier: Modifier,
    imageId: Int,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(modifier = modifier) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = imageId),
            contentDescription = contentDescription,
            contentScale = contentScale
        )
    }
}