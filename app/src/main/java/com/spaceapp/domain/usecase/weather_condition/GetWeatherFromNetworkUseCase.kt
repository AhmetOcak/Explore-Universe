package com.spaceapp.domain.usecase.weather_condition

import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.call
import com.spaceapp.domain.model.weather_condition.WeatherCondition
import com.spaceapp.data.repository.weather.WeatherConditionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherFromNetworkUseCase @Inject constructor(private val weatherRepository: WeatherConditionRepository) {

    operator fun invoke(latitude: Double, longitude: Double): Flow<Response<WeatherCondition>> =
        call {
            weatherRepository.getWeatherConditionFromNetwork(
                latitude = latitude,
                longitude = longitude
            )
        }
}