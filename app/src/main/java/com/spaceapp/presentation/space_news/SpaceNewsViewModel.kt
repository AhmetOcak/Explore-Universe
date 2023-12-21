package com.spaceapp.presentation.space_news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Response
import com.spaceapp.domain.usecase.location.GetLocationUseCase
import com.spaceapp.domain.usecase.space_news.*
import com.spaceapp.domain.usecase.weather_condition.*
import com.spaceapp.presentation.space_news.state.ScienceNewsState
import com.spaceapp.presentation.space_news.state.SpaceNewsState
import com.spaceapp.presentation.space_news.state.WeatherConditionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
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
    private val getLocationUseCase: GetLocationUseCase,
    private val getLatestScienceNewsFromNetworkUseCase: GetLatestScienceNewsFromNetworkUseCase
) : ViewModel() {

    private val _spaceNewsState = MutableStateFlow<SpaceNewsState>(SpaceNewsState.Loading)
    val spaceNewsState = _spaceNewsState.asStateFlow()

    private val _weatherConditionState = MutableStateFlow<WeatherConditionState>(
        WeatherConditionState.Loading
    )

    private val _scienceNewsState = MutableStateFlow<ScienceNewsState>(ScienceNewsState.Loading)
    val scienceNewsState = _scienceNewsState.asStateFlow()

    val weatherConditionState = _weatherConditionState.asStateFlow()

    init {
        getScienceNewsFromNetwork()
        getSpaceNewsFromNetwork()
        //loadLocation()
    }

    fun getSpaceNewsFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getSpaceNewsFromNetworkUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _spaceNewsState.value = SpaceNewsState.Loading
                    }

                    is Response.Success -> {
                        _spaceNewsState.value =
                            SpaceNewsState.Success(data = result.data?.articles ?: listOf())
                        if (result.data != null) {
                            //clearLocalSpaceNews()
                            //addSpaceNewsToDatabaseUseCase(spaceNews = result.data)
                        }
                    }

                    is Response.Error -> {
                        //getSpaceNewsFromLocal()
                        Log.d("ERROR", result.message ?: "ERROR")
                    }
                }
            }
        }
    }

    fun getScienceNewsFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getLatestScienceNewsFromNetworkUseCase().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _scienceNewsState.value = ScienceNewsState.Loading
                    }

                    is Response.Success -> {
                        _scienceNewsState.value =
                            ScienceNewsState.Success(response.data?.articles ?: listOf())
                    }

                    is Response.Error -> {
                        _scienceNewsState.value = ScienceNewsState.Error(response.message ?: "ERROR")
                    }
                }
            }
        }
    }

    /*private fun getSpaceNewsFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getSpaceNewsFromDatabaseUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _spaceNewsState.value = SpaceNewsState.Loading
                    }
                    is Response.Success -> {
                        _spaceNewsState.value = SpaceNewsState.Success(data = result.data)
                    }
                    is Response.Error -> {
                        _spaceNewsState.value = SpaceNewsState.Error(errorMessage = result.message)
                    }
                }
            }
        }
    }*/

    private fun loadLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _weatherConditionState.value = WeatherConditionState.Loading
                    }

                    is Response.Success -> {
                        if (result.data != null) {
                            getWeatherFromNetwork(
                                latitude = result.data.latitude,
                                longitude = result.data.longitude
                            )
                        }
                    }

                    is Response.Error -> {
                        _weatherConditionState.value = WeatherConditionState.Nothing
                    }
                }
            }
        }
    }

    private fun getWeatherFromNetwork(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherFromNetworkUseCase(
                latitude = latitude,
                longitude = longitude
            ).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _weatherConditionState.value = WeatherConditionState.Loading
                    }

                    is Response.Success -> {
                        _weatherConditionState.value =
                            WeatherConditionState.Success(data = result.data)

                        if (result.data != null) {
                            getWeatherFromDatabaseUseCase().collect {
                                when (it) {
                                    is Response.Success -> {
                                        updateWeatherUseCase.invoke(weatherCondition = result.data)
                                    }

                                    is Response.Error -> {
                                        addWeatherToDatabaseUseCase.invoke(weatherCondition = result.data)
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }

                    is Response.Error -> {
                        getWeatherFromDatabase()
                    }
                }
            }
        }
    }

    private fun getWeatherFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherFromDatabaseUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _weatherConditionState.value = WeatherConditionState.Loading
                    }

                    is Response.Success -> {
                        _weatherConditionState.value =
                            WeatherConditionState.Success(data = result.data)
                    }

                    is Response.Error -> {
                        _weatherConditionState.value = WeatherConditionState.Nothing
                    }
                }
            }
        }
    }
}