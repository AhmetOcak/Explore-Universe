package com.spaceapp.domain.usecase.weather_condition

import com.spaceapp.data.repository.WeatherConditionRepositoryImpl
import com.spaceapp.domain.model.weather_condition.WeatherCondition
import javax.inject.Inject

class UpdateWeatherUseCase @Inject constructor(private val weatherRepository: WeatherConditionRepositoryImpl) {

    suspend operator fun invoke(weatherCondition: WeatherCondition) =
        weatherRepository.updateWeatherCondition(weatherCondition = weatherCondition)
}