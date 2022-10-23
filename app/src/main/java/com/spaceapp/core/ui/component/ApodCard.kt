package com.spaceapp.core.ui.component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R

@Composable
fun ApodCard(
    modifier: Modifier = Modifier,
    imagePadding: PaddingValues = PaddingValues(0.dp),
    contentScale: ContentScale = ContentScale.Fit,
    backgroundColor: Color = MaterialTheme.colors.surface,
    context: Context,
    title: String,
    imageUrl: String
) {
    val imageRequest = ImageRequest.Builder(context = context)
        .data(imageUrl)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .build()

    Box {
        Card(
            modifier = modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                .height(320.dp),
            shape = RoundedCornerShape(32.dp),
            elevation = 4.dp,
            backgroundColor = backgroundColor
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(6f)
                        .padding(imagePadding),
                    model = imageRequest,
                    imageLoader = context.imageLoader,
                    contentDescription = null,
                    contentScale = contentScale
                )
                Text(
                    modifier = modifier
                        .padding(start = 16.dp, bottom = 16.dp, top = 16.dp)
                        .weight(2f),
                    text = title,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}