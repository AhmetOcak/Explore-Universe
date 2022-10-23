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
import com.spaceapp.core.ui.theme.White

@OptIn(ExperimentalMaterialApi::class)
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
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        elevation = 8.dp
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
                style = MaterialTheme.typography.button.copy(
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
                    style = MaterialTheme.typography.button.copy(
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