package com.spaceapp.domain.usecase.weather_condition

import com.spaceapp.domain.model.weather_condition.WeatherCondition
import com.spaceapp.data.repository.weather.WeatherConditionRepository
import javax.inject.Inject

class AddWeatherToDatabaseUseCase @Inject constructor(private val weatherRepository: WeatherConditionRepository) {

    suspend operator fun invoke(weatherCondition: WeatherCondition) =
        weatherRepository.addWeatherConditionToLocal(weatherCondition = weatherCondition)
}