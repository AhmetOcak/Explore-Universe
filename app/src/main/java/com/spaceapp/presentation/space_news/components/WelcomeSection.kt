package com.spaceapp.presentation.space_news.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.LoadingSpinner
import com.spaceapp.presentation.space_news.state.WeatherConditionState
import com.spaceapp.presentation.utils.NewsScreenConstants

@Composable
fun WelcomeSection(
    modifier: Modifier,
    weatherConditionState: WeatherConditionState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "HELLO EXPLORER", style = MaterialTheme.typography.h1)
            Text(text = NewsScreenConstants.welcome_subtitle, style = MaterialTheme.typography.h4)
        }
        CurrentWeatherInfo(modifier = modifier, weatherConditionState = weatherConditionState)
    }
}

@Composable
private fun CurrentWeatherInfo(modifier: Modifier, weatherConditionState: WeatherConditionState) {
    when (weatherConditionState) {
        is WeatherConditionState.Loading -> {
            LoadingSpinner(modifier = modifier.fillMaxWidth())
        }
        is WeatherConditionState.Success -> {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
            ) {
                Image(
                    modifier = modifier
                        .size(32.dp)
                        .padding(end = 4.dp),
                    painter = painterResource(id = R.drawable.broken_clouds_day),
                    contentDescription = null,
                )
                Text(
                    text = "${weatherConditionState.data!!.temp.temp.toInt()}°C",
                    fontSize = 24.sp
                )
            }
        }
        is WeatherConditionState.Nothing -> {}
    }
}