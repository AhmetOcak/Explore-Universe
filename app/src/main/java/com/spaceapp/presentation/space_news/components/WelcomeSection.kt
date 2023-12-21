package com.spaceapp.presentation.space_news.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spaceapp.R
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.presentation.space_news.state.WeatherConditionState
import com.spaceapp.presentation.utils.NewsScreenConstants

@Composable
fun WelcomeSection(weatherConditionState: WeatherConditionState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "HELLO EXPLORER", style = MaterialTheme.typography.headlineLarge)
            Text(text = NewsScreenConstants.welcome_subtitle, style = MaterialTheme.typography.displayMedium)
        }
        CurrentWeatherInfo(weatherConditionState = weatherConditionState)
    }
}

@Composable
private fun CurrentWeatherInfo(weatherConditionState: WeatherConditionState) {
    when (weatherConditionState) {
        is WeatherConditionState.Loading -> {
            LoadingSpinner(modifier = Modifier.fillMaxWidth())
        }
        is WeatherConditionState.Success -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
            ) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 4.dp),
                    painter = painterResource(id = R.drawable.broken_clouds_day),
                    contentDescription = null,
                )
                Text(
                    text = "${weatherConditionState.data!!.temp.temp.toInt()}°C",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp)
                )
            }
        }
        is WeatherConditionState.Nothing -> {}
    }
}