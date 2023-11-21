package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.spaceapp.R
import com.spaceapp.core.common.helper.GifLoader

@Composable
fun LoadingSpinner(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(50)),
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data = R.drawable.loading).build(),
                imageLoader = GifLoader.gifLoader(context = LocalContext.current)
            ),
            contentDescription = null,
            contentScale = contentScale
        )
    }
}