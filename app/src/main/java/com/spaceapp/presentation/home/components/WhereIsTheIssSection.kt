package com.spaceapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.common.EpochConverter
import com.spaceapp.core.designsystem.component.ErrorCard
import com.spaceapp.core.designsystem.component.Gif
import com.spaceapp.core.designsystem.component.LoadingSpinner
import com.spaceapp.core.designsystem.component.Underline
import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.presentation.home.HomeViewModel
import com.spaceapp.presentation.home.state.WhereIsTheIssState
import com.spaceapp.presentation.utils.HomeScreenConstants

@Composable
fun WhereIsTheIssSection(
    modifier: Modifier,
    whereIsTheIssState: WhereIsTheIssState,
    viewModel: HomeViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = HomeScreenConstants.where_is_the_iss_title,
            style = MaterialTheme.typography.h1
        )
        when (whereIsTheIssState) {
            is WhereIsTheIssState.Loading -> {
                LoadingSpinner(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                )
            }
            is WhereIsTheIssState.Success -> {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    IssPositionInfo(
                        modifier = modifier,
                        data = whereIsTheIssState.data!!,
                    )
                }
            }
            is WhereIsTheIssState.Error -> {
                ErrorCard(
                    errorDescription = whereIsTheIssState.errorMessage!!,
                    paddingValues = PaddingValues(top = 16.dp),
                    isButtonAvailable = true,
                    buttonText = "Try Again",
                    onClick = { viewModel.getIssPositionFromNetwork() }
                )
            }
        }
    }
}

@Composable
private fun IssPositionInfo(modifier: Modifier, data: Iss) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Gif(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp),
            gifId = R.drawable.iss
        )
        Text(
            modifier = modifier.padding(start = 16.dp, top = 16.dp),
            text = EpochConverter.readTimestamp(data.date),
            style = MaterialTheme.typography.h4
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = "The ISS is ${data.altitude.toInt()}km above the Earth",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = HomeScreenConstants.iss_speed,
                    style = MaterialTheme.typography.h4
                )
                Underline(width = 64.dp)
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = "${data.velocity.toInt()}km",
                    style = MaterialTheme.typography.body1
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = HomeScreenConstants.iss_visibility,
                    style = MaterialTheme.typography.h4
                )
                Underline(width = 64.dp)
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = data.visibility,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}