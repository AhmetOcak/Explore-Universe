package com.spaceapp.presentation.explore

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Response
import com.spaceapp.domain.usecase.apod.AddApodToDatabaseUseCase
import com.spaceapp.domain.usecase.apod.ClearApodDatabaseUseCase
import com.spaceapp.domain.usecase.apod.GetApodFromDatabaseUseCase
import com.spaceapp.domain.usecase.apod.GetApodFromNetworkUseCase
import com.spaceapp.domain.usecase.explore_galaxy.GetExploreGalaxyDataUseCase
import com.spaceapp.presentation.explore.state.ApodState
import com.spaceapp.presentation.explore.state.ExploreCategoryState
import com.spaceapp.presentation.explore.state.ExploreGalaxyState
import com.spaceapp.presentation.utils.ExploreCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getApodFromNetworkUseCase: GetApodFromNetworkUseCase,
    private val getApodFromLocalUseCase: GetApodFromDatabaseUseCase,
    private val clearLocalApodUseCase: ClearApodDatabaseUseCase,
    private val addApodToDatabaseUseCase: AddApodToDatabaseUseCase,
    private val getExploreGalaxyDataUseCase: GetExploreGalaxyDataUseCase,
    @ApplicationContext applicationContext: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getApodFromNetwork()
        getExploreGalaxyData(applicationContext = applicationContext)
    }

    fun exploreCategoryOnClick(categoryName: String) {
        when (categoryName) {
            ExploreCategories.all -> {
                _uiState.update {
                    it.copy(exploreCategoryState = ExploreCategoryState.ALL)
                }
            }
            ExploreCategories.meteors -> {
                _uiState.update {
                    it.copy(exploreCategoryState = ExploreCategoryState.METEORS)
                }
            }
            ExploreCategories.planets -> {
                _uiState.update {
                    it.copy(exploreCategoryState = ExploreCategoryState.PLANETS)
                }
            }
            ExploreCategories.moons -> {
                _uiState.update {
                    it.copy(exploreCategoryState = ExploreCategoryState.MOONS)
                }
            }
            ExploreCategories.comets -> {
                _uiState.update {
                    it.copy(exploreCategoryState = ExploreCategoryState.COMETS)
                }
            }
            ExploreCategories.stars -> {
                _uiState.update {
                    it.copy(exploreCategoryState = ExploreCategoryState.STARS)
                }
            }
        }
    }

    private fun getExploreGalaxyData(applicationContext: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            getExploreGalaxyDataUseCase(applicationContext = applicationContext).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(exploreGalaxyState = ExploreGalaxyState.Loading)
                        }
                    }
                    is Response.Success -> {
                        _uiState.update {
                            it.copy(exploreGalaxyState = ExploreGalaxyState.Success(data = result.data))
                        }
                    }
                    is Response.Error -> {
                        _uiState.update {
                            it.copy(exploreGalaxyState = ExploreGalaxyState.Error(errorMessage = result.message))
                        }
                    }
                }
            }
        }
    }


    private fun getApodFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getApodFromNetworkUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(apodState = ApodState.Loading)
                        }
                    }
                    is Response.Success -> {
                        _uiState.update {
                            it.copy(apodState = ApodState.Success(apodData = result.data))
                        }
                        if (result.data != null) {
                            clearLocalApodUseCase()
                            addApodToDatabaseUseCase(apod = result.data)
                        }
                    }
                    is Response.Error -> {
                        getApodFromLocal()
                    }
                }
            }
        }
    }

    private fun getApodFromLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            getApodFromLocalUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(apodState = ApodState.Loading)
                        }
                    }
                    is Response.Success -> {
                        _uiState.update {
                            it.copy(apodState = ApodState.Success(apodData = result.data))
                        }
                    }
                    is Response.Error -> {
                        _uiState.update {
                            it.copy(apodState = ApodState.Error(errorMessage = result.message))
                        }
                    }
                }
            }
        }
    }
}

data class UiState(
    val apodState: ApodState = ApodState.Loading,
    val exploreGalaxyState: ExploreGalaxyState = ExploreGalaxyState.Loading,
    val exploreCategoryState: ExploreCategoryState = ExploreCategoryState.ALL
)