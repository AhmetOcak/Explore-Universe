package com.spaceapp.core.ui.component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    newsImageUrl: String,
    height: Dp = 125.dp,
    newsTime: String,
    newsTitle: String,
    newsAuthor: String,
    context: Context
) {
    val imageRequest = ImageRequest.Builder(context = context)
        .data(newsImageUrl)
        .memoryCacheKey(newsImageUrl)
        .diskCacheKey(newsImageUrl)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .build()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(125.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        onClick = onClick
    ) {
        Row(modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.Start) {
            AsyncImage(
                modifier = modifier
                    .weight(2f)
                    .height(height),
                model = imageRequest,
                imageLoader = context.imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .weight(4f)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = modifier
                            .padding(end = 4.dp)
                            .size(18.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_access_time),
                        contentDescription = null
                    )
                    Text(text = newsTime, style = MaterialTheme.typography.body2)
                }
                Text(
                    text = newsTitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1
                )
                Text(text = newsAuthor, style = MaterialTheme.typography.body2)
            }
        }
    }
}