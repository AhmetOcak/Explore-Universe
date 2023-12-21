package com.spaceapp.presentation.home.components

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.spaceapp.R
import com.spaceapp.core.designsystem.components.ErrorCard
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.core.designsystem.components.Underline
import com.spaceapp.core.designsystem.theme.TransparentKimberly
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.presentation.home.state.MarsPhotoState
import com.spaceapp.presentation.utils.HomeScreenConstants

@Composable
fun MarsPhotosSection(
    marsPhotoState: MarsPhotoState,
    dataComingFromDb: Boolean,
    retryMarsPhotoData: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        MarsPhotosTitle(modifier = Modifier.padding(16.dp))
        when (marsPhotoState) {
            is MarsPhotoState.Loading -> {
                LoadingSpinner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, bottom = 16.dp)
                )
            }

            is MarsPhotoState.Success -> {
                MarsPhotoList(
                    dataComingFromDb = dataComingFromDb,
                    photoList = marsPhotoState.data!!,
                )
            }

            is MarsPhotoState.Error -> {
                ErrorCard(
                    errorDescription = marsPhotoState.errorMessage!!,
                    paddingValues = PaddingValues(16.dp),
                    isButtonAvailable = true,
                    buttonText = "Try Again",
                    onClick = retryMarsPhotoData
                )
            }
        }
    }
}

@Composable
private fun MarsPhotosTitle(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = HomeScreenConstants.photos_from_mars_title,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
private fun MarsPhotoList(
    dataComingFromDb: Boolean,
    photoList: List<MarsPhoto>,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (dataComingFromDb) {
            items(photoList, key = { it.photos[0].image }) {
                MarsCard(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                        .height(300.dp),
                    earthDate = it.photos[0].date,
                    rover = it.photos[0].rover.name,
                    marsImageUrl = it.photos[0].image,
                )
            }
        } else {
            items(photoList[0].photos, key = { it.image }) {
                MarsCard(
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                        .height(300.dp),
                    earthDate = it.date,
                    rover = it.rover.name,
                    marsImageUrl = it.image
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
    marsImageUrl: String
) {
    val imageRequest = ImageRequest.Builder(context = LocalContext.current)
        .data(marsImageUrl)
        .memoryCacheKey(marsImageUrl)
        .diskCacheKey(marsImageUrl)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .build()

    Card(
        modifier = modifier,
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
                imageLoader = LocalContext.current.imageLoader,
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
                Rover(rover)
                Date(earthDate)
            }
        }
    }
}

@Composable
private fun Date(earthDate: String) {
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

@Composable
private fun Rover(rover: String) {
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
}