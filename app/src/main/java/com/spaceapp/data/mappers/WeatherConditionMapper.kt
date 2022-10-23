package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.db.entity.WeatherConditionEntity
import com.spaceapp.data.datasource.remote.weather_condition.entity.MainDto
import com.spaceapp.data.datasource.remote.weather_condition.entity.WeatherConditionDto
import com.spaceapp.data.datasource.remote.weather_condition.entity.WeatherDescriptionDto
import com.spaceapp.domain.model.Main
import com.spaceapp.domain.model.WeatherCondition
import com.spaceapp.domain.model.WeatherDescription

fun WeatherConditionDto.toWeatherCondition(): WeatherCondition {
    return WeatherCondition(
        weatherDescription = weatherDescription.map {
            WeatherDescription(
                mainDescription = it.mainDescription,
                description = it.description
            )
        },
        temp = Main(temp = temp.temp)
    )
}

fun WeatherConditionEntity.toWeatherCondition(): WeatherCondition {
    return WeatherCondition(
        weatherDescription = listOf(
            WeatherDescription(
                mainDescription = mainDescription,
                description = description
            )
        ),
        temp = Main(temp = temp)
    )
}

fun WeatherCondition.toWeatherConditionEntity(): WeatherConditionEntity {
    return WeatherConditionEntity(
        id = 0,
        mainDescription = weatherDescription[0].mainDescription,
        description = weatherDescription[0].description,
        temp = temp.temp
    )
}