package com.spaceapp.presentation.space_news.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R
import com.spaceapp.core.designsystem.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LatestNewsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    newsImageUrl: String,
    newsTitle: String,
    newsAuthor: String,
    context: Context
) {
    val imageRequest = ImageRequest.Builder(context = context)
        .data(newsImageUrl)
        .memoryCacheKey(newsImageUrl)
        .diskCacheKey(newsImageUrl)
        .build()

    Card(
        modifier = modifier
            .height(200.dp)
            .width(LocalConfiguration.current.screenWidthDp.dp - 64.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            AsyncImage(
                modifier = modifier.fillMaxSize(),
                model = imageRequest,
                imageLoader = context.imageLoader,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = modifier.padding(bottom = 16.dp),
                text = newsTitle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 16.sp,
                    color = White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    modifier = modifier
                        .padding(end = 4.dp, top = 8.dp)
                        .size(18.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_create),
                    contentDescription = null,
                    tint = White,
                )
                Text(
                    text = newsAuthor,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 16.sp,
                        color = White,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}