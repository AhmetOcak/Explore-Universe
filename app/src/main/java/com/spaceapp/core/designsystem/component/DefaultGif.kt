package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.spaceapp.core.common.helper.GifLoader

@Composable
fun Gif(modifier: Modifier, gifId: Int, contentScale: ContentScale = ContentScale.Crop) {
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data = gifId).build(),
            imageLoader = GifLoader.gifLoader(context = LocalContext.current)
        ),
        contentDescription = null,
        contentScale = contentScale
    )
}