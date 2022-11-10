package com.spaceapp.core.ui.component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R

@Composable
fun MarsCard(
    modifier: Modifier,
    rover: String,
    earthDate: String,
    marsImageUrl: String,
    context: Context
) {
    val imageRequest = ImageRequest.Builder(context = context)
        .data(marsImageUrl)
        .memoryCacheKey(marsImageUrl)
        .diskCacheKey(marsImageUrl)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .build()

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f),
                model = imageRequest,
                imageLoader = context.imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "rover", style = MaterialTheme.typography.h4)
                    Underline(width = 96.dp)
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = rover,
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "earth date", style = MaterialTheme.typography.h4)
                    Underline(width = 96.dp)
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = earthDate,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}