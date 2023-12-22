package com.spaceapp.presentation.space_news.state

import com.spaceapp.domain.model.weather_condition.WeatherCondition

sealed interface WeatherConditionState {
    data class Success(val data: WeatherCondition?): WeatherConditionState
    data object Loading: WeatherConditionState
    data object Nothing: WeatherConditionState
}