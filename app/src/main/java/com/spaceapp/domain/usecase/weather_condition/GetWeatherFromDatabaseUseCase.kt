package com.spaceapp.domain.usecase.weather_condition

import com.spaceapp.core.common.Response
import com.spaceapp.domain.model.weather_condition.WeatherCondition
import com.spaceapp.data.repository.weather.WeatherConditionRepository
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException
import javax.inject.Inject

class GetWeatherFromDatabaseUseCase @Inject constructor(private val weatherRepository: WeatherConditionRepository) {

    operator fun invoke(): Flow<Response<WeatherCondition>> =
        flow {
            try {
                emit(Response.Loading)

                emit(Response.Success(data = weatherRepository.getWeatherConditionFromLocal()))
            } catch (e: NullPointerException) {
                emit(Response.Error(message = ERROR.INTERNET))
            } catch (e: Exception) {
                emit(Response.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
            }
        }
}