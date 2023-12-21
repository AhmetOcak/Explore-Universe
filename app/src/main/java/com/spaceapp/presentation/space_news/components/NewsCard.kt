package com.spaceapp.presentation.space_news.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.spaceapp.core.designsystem.theme.TransparentKimberly

@OptIn(ExperimentalMaterial3Api::class)
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
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = TransparentKimberly)
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
                    Text(text = newsTime, style = MaterialTheme.typography.bodySmall)
                }
                Text(
                    text = newsTitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = newsAuthor, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}