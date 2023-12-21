package com.spaceapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.common.helper.EpochConverter
import com.spaceapp.core.designsystem.components.ErrorCard
import com.spaceapp.core.designsystem.components.Gif
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.core.designsystem.components.Underline
import com.spaceapp.core.designsystem.theme.TransparentKimberly
import com.spaceapp.presentation.home.state.IssState
import com.spaceapp.presentation.utils.HomeScreenConstants

@Composable
fun WhereIsTheIssSection(
    issState: IssState,
    retryIssPositionInfo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = HomeScreenConstants.where_is_the_iss_title,
            style = MaterialTheme.typography.headlineLarge
        )
        when (issState) {
            is IssState.Loading -> {
                LoadingSpinner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                )
            }
            is IssState.Success -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = TransparentKimberly)
                ) {
                    IssPositionInfo(data = issState.data!!)
                }
            }
            is IssState.Error -> {
                ErrorCard(
                    errorDescription = issState.errorMessage!!,
                    paddingValues = PaddingValues(top = 16.dp),
                    isButtonAvailable = true,
                    buttonText = "Try Again",
                    onClick = retryIssPositionInfo
                )
            }
        }
    }
}

@Composable
private fun IssPositionInfo(data: com.spaceapp.domain.model.where_is_the_iss.Iss) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Gif(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            gifId = R.drawable.iss
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = EpochConverter.readTimestamp(data.date),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = "The ISS is ${data.altitude.toInt()}km above the Earth",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = HomeScreenConstants.iss_speed,
                    style = MaterialTheme.typography.displayMedium
                )
                Underline(width = 64.dp)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "${data.velocity.toInt()}km",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = HomeScreenConstants.iss_visibility,
                    style = MaterialTheme.typography.displayMedium
                )
                Underline(width = 64.dp)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = data.visibility,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}