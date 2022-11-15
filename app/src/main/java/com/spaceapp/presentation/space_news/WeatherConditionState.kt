package com.spaceapp.presentation.space_news

import com.spaceapp.domain.model.weather_condition.WeatherCondition

sealed interface WeatherConditionState {
    data class Success(val data: WeatherCondition?): WeatherConditionState
    object Loading: WeatherConditionState
    object Nothing: WeatherConditionState
}