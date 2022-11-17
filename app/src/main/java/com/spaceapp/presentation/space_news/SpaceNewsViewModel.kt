package com.spaceapp.presentation.space_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Result
import com.spaceapp.domain.usecase.location.GetLocationUseCase
import com.spaceapp.domain.usecase.space_news.*
import com.spaceapp.domain.usecase.weather_condition.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceNewsViewModel @Inject constructor(
    private val getSpaceNewsFromNetworkUseCase: GetSpaceNewsFromNetworkUseCase,
    private val getSpaceNewsFromDatabaseUseCase: GetSpaceNewsFromDatabaseUseCase,
    private val addSpaceNewsToDatabaseUseCase: AddSpaceNewsToDatabaseUseCase,
    private val clearLocalSpaceNews: ClearSpaceNewsDatabaseUseCase,
    private val getWeatherFromNetworkUseCase: GetWeatherFromNetworkUseCase,
    private val getWeatherFromDatabaseUseCase: GetWeatherFromDatabaseUseCase,
    private val addWeatherToDatabaseUseCase: AddWeatherToDatabaseUseCase,
    private val updateWeatherUseCase: UpdateWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _spaceNewsState = MutableStateFlow<SpaceNewsState>(SpaceNewsState.Loading)
    val spaceNewsState = _spaceNewsState.asStateFlow()

    private val _weatherConditionState =
        MutableStateFlow<WeatherConditionState>(WeatherConditionState.Loading)
    val weatherConditionState = _weatherConditionState.asStateFlow()

    init {
        getSpaceNewsFromNetwork()
        loadLocation()
    }

    fun getSpaceNewsFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        getSpaceNewsFromNetworkUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _spaceNewsState.value = SpaceNewsState.Loading
                }
                is Result.Success -> {
                    _spaceNewsState.value = SpaceNewsState.Success(data = result.data)
                    if (result.data != null) {
                        clearLocalSpaceNews()
                        addSpaceNewsToDatabaseUseCase(spaceNews = result.data)
                    }
                }
                is Result.Error -> {
                    getSpaceNewsFromLocal()
                }
            }
        }
    }

    private fun getSpaceNewsFromLocal() = viewModelScope.launch(Dispatchers.IO) {
        getSpaceNewsFromDatabaseUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _spaceNewsState.value = SpaceNewsState.Loading
                }
                is Result.Success -> {
                    _spaceNewsState.value = SpaceNewsState.Success(data = result.data)
                }
                is Result.Error -> {
                    _spaceNewsState.value = SpaceNewsState.Error(errorMessage = result.message)
                }
            }
        }
    }

    private fun loadLocation() = viewModelScope.launch(Dispatchers.IO) {
        getLocationUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> { _weatherConditionState.value = WeatherConditionState.Loading
                }
                is Result.Success -> {
                    if (result.data != null) {
                        getWeatherFromNetwork(
                            latitude = result.data.latitude,
                            longitude = result.data.longitude
                        )
                    }
                }
                is Result.Error -> {
                    _weatherConditionState.value = WeatherConditionState.Nothing
                }
            }
        }
    }

    private fun getWeatherFromNetwork(latitude: Double, longitude: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherFromNetworkUseCase(
                latitude = latitude,
                longitude = longitude
            ).collect() { result ->
                when (result) {
                    is Result.Loading -> {
                        _weatherConditionState.value = WeatherConditionState.Loading
                    }
                    is Result.Success -> {
                        _weatherConditionState.value = WeatherConditionState.Success(data = result.data)

                        if (result.data != null) {
                            getWeatherFromDatabaseUseCase().collect() {
                                when (it) {
                                    is Result.Success -> {
                                        updateWeatherUseCase.invoke(weatherCondition = result.data)
                                    }
                                    is Result.Error -> {
                                        addWeatherToDatabaseUseCase.invoke(weatherCondition = result.data)
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }
                    is Result.Error -> {
                        getWeatherFromDatabase()
                    }
                }
            }
        }

    private fun getWeatherFromDatabase() = viewModelScope.launch(Dispatchers.IO) {
        getWeatherFromDatabaseUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _weatherConditionState.value = WeatherConditionState.Loading
                }
                is Result.Success -> {
                    _weatherConditionState.value = WeatherConditionState.Success(data = result.data)
                }
                is Result.Error -> {
                    _weatherConditionState.value = WeatherConditionState.Nothing
                }
            }
        }
    }
}