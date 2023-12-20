package com.spaceapp.presentation.home.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.ErrorCard
import com.spaceapp.core.designsystem.component.LoadingSpinner
import com.spaceapp.core.designsystem.component.Underline
import com.spaceapp.core.designsystem.theme.TransparentKimberly
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.presentation.home.state.MarsPhotoState
import com.spaceapp.presentation.utils.HomeScreenConstants

@Composable
fun MarsPhotosSection(
    modifier: Modifier,
    marsPhotoState: MarsPhotoState,
    context: Context,
    dataComingFromDb: Boolean,
    marsPhotoErrorOnClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        MarsPhotosTitle(modifier = modifier)
        when (marsPhotoState) {
            is MarsPhotoState.Loading -> {
                LoadingSpinner(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, bottom = 16.dp)
                )
            }

            is MarsPhotoState.Success -> {
                MarsPhotoList(
                    modifier = modifier,
                    dataComingFromDb = dataComingFromDb,
                    photoList = marsPhotoState.data!!,
                    context = context
                )
            }

            is MarsPhotoState.Error -> {
                ErrorCard(
                    errorDescription = marsPhotoState.errorMessage!!,
                    paddingValues = PaddingValues(top = 16.dp),
                    isButtonAvailable = true,
                    buttonText = "Try Again",
                    onClick = marsPhotoErrorOnClick
                )
            }
        }
    }
}

@Composable
private fun MarsPhotosTitle(modifier: Modifier) {
    Text(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
        text = HomeScreenConstants.photos_from_mars_title,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
private fun MarsPhotoList(
    modifier: Modifier,
    dataComingFromDb: Boolean,
    photoList: List<MarsPhoto>,
    context: Context
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (dataComingFromDb) {
            items(photoList) {
                MarsCard(
                    modifier = modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                        .height(300.dp),
                    earthDate = it.photos[0].date,
                    rover = it.photos[0].rover.name,
                    marsImageUrl = it.photos[0].image,
                    context = context
                )
            }
        } else {
            items(photoList[0].photos) {
                MarsCard(
                    modifier = modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                        .height(300.dp),
                    earthDate = it.date,
                    rover = it.rover.name,
                    marsImageUrl = it.image,
                    context = context
                )
            }
        }
    }
}

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
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = TransparentKimberly)
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
                    Text(text = "rover", style = MaterialTheme.typography.displayMedium)
                    Underline(width = 96.dp)
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = rover,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "earth date", style = MaterialTheme.typography.displayMedium)
                    Underline(width = 96.dp)
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = earthDate,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}