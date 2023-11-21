package com.spaceapp.presentation.explore.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.ErrorCard
import com.spaceapp.core.designsystem.component.LoadingSpinner
import com.spaceapp.presentation.explore.state.ApodState
import com.spaceapp.presentation.utils.ExploreScreenConstants

@Composable
fun UniverseImageSection(modifier: Modifier, apodState: ApodState) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp),
            text = ExploreScreenConstants.title_2,
            style = MaterialTheme.typography.h2
        )
        when (apodState) {
            is ApodState.Loading -> {
                LoadingSpinner(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                )
            }
            is ApodState.Success -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(apodState.apodData!!) {
                        ApodCard(
                            title = it.title,
                            context = LocalContext.current,
                            imageUrl = it.image,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            is ApodState.Error -> {
                ErrorCard(errorDescription = apodState.errorMessage.toString())
            }
        }
    }
}

@Composable
private fun ApodCard(
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