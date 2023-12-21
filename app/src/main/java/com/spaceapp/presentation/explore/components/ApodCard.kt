package com.spaceapp.presentation.explore.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    imagePadding: PaddingValues = PaddingValues(0.dp),
    contentScale: ContentScale = ContentScale.Fit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
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
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                .height(320.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(6f)
                        .padding(imagePadding),
                    model = imageRequest,
                    imageLoader = context.imageLoader,
                    contentDescription = null,
                    contentScale = contentScale
                )
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 16.dp, top = 16.dp)
                        .weight(2f),
                    text = title,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}