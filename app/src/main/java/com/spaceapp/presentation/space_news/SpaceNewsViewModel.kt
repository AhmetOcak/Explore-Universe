package com.spaceapp.presentation.space_news

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
    private val getLatestScienceNewsFromNetworkUseCase: GetLatestScienceNewsFromNetworkUseCase,
    private val addScienceNewsToDatabaseUseCase: AddScienceNewsToDatabaseUseCase,
    private val clearScienceNewsDbUseCase: ClearScienceNewsDbUseCase,
    private val getScienceNewsFromDb: GetScienceNewsFromLocal
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getScienceNewsFromNetwork()
        getSpaceNewsFromNetwork()
        loadLocation()
    }

    fun getSpaceNewsFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getSpaceNewsFromNetworkUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(spaceNewsState = SpaceNewsState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(
                                spaceNewsState = SpaceNewsState.Success(
                                    data = result.data?.articles ?: listOf()
                                )
                            )
                        }
                        if (result.data != null) {
                            clearLocalSpaceNews()
                            addSpaceNewsToDatabaseUseCase(spaceNews = result.data)
                        }
                    }

                    is Response.Error -> {
                        getSpaceNewsFromLocal()
                    }
                }
            }
        }
    }

    private fun getScienceNewsFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getLatestScienceNewsFromNetworkUseCase().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(scienceNewsState = ScienceNewsState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(
                                scienceNewsState = ScienceNewsState.Success(
                                    response.data?.articles ?: listOf()
                                )
                            )
                        }
                        if (response.data != null) {
                            clearScienceNewsDbUseCase()
                            addScienceNewsToDatabaseUseCase(spaceNews = response.data)
                        }
                    }

                    is Response.Error -> {
                        getScienceNewsFromLocal()
                    }
                }
            }
        }
    }

    private fun getSpaceNewsFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getSpaceNewsFromDatabaseUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(spaceNewsState = SpaceNewsState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(
                                spaceNewsState = SpaceNewsState.Success(
                                    data = result.data?.articles ?: listOf()
                                )
                            )
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(
                                spaceNewsState = SpaceNewsState.Error(
                                    errorMessage = result.message ?: "error"
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getScienceNewsFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getScienceNewsFromDb().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(scienceNewsState = ScienceNewsState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(
                                scienceNewsState = ScienceNewsState.Success(
                                    data = result.data?.articles ?: listOf()
                                )
                            )
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(
                                scienceNewsState = ScienceNewsState.Error(
                                    errorMessage = result.message ?: "error"
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Loading)
                        }
                    }

                    is Response.Success -> {
                        if (result.data != null) {
                            getWeatherFromNetwork(
                                latitude = result.data.latitude,
                                longitude = result.data.longitude
                            )
                        } else {
                            _uiState.update {
                                it.copy(weatherConditionState = WeatherConditionState.Nothing)
                            }
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Nothing)
                        }
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
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Success(data = result.data))
                        }

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
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Success(data = result.data))
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(weatherConditionState = WeatherConditionState.Nothing)
                        }
                    }
                }
            }
        }
    }
}

data class UiState(
    val scienceNewsState: ScienceNewsState = ScienceNewsState.Nothing,
    val spaceNewsState: SpaceNewsState = SpaceNewsState.Nothing,
    val weatherConditionState: WeatherConditionState = WeatherConditionState.Nothing
)