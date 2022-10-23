package com.spaceapp.domain.usecase.weather_condition

import com.spaceapp.data.repository.WeatherConditionRepositoryImpl
import com.spaceapp.domain.model.WeatherCondition
import javax.inject.Inject

class AddWeatherToDatabaseUseCase @Inject constructor(private val weatherRepository: WeatherConditionRepositoryImpl) {

    suspend operator fun invoke(weatherCondition: WeatherCondition) =
        weatherRepository.addWeatherConditionToLocal(weatherCondition = weatherCondition)
}