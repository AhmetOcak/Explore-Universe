package com.spaceapp.presentation.explore

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Result
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
import kotlinx.coroutines.flow.asStateFlow
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

    private val _apodState = MutableStateFlow<ApodState>(ApodState.Loading)
    val apodState = _apodState.asStateFlow()

    private val _exploreGalaxyState = MutableStateFlow<ExploreGalaxyState>(ExploreGalaxyState.Loading)
    val exploreGalaxyState = _exploreGalaxyState.asStateFlow()

    private val _exploreCategoryState = MutableStateFlow<ExploreCategoryState>(ExploreCategoryState.ALL)
    val exploreCategoryState = _exploreCategoryState.asStateFlow()

    init {
        getApodFromNetwork()
        getExploreGalaxyData(applicationContext = applicationContext)
    }

    fun exploreCategoryOnClick(categoryName: String) {
        when (categoryName) {
            ExploreCategories.all -> {
                _exploreCategoryState.value = ExploreCategoryState.ALL
            }
            ExploreCategories.meteors -> {
                _exploreCategoryState.value = ExploreCategoryState.METEORS
            }
            ExploreCategories.planets -> {
                _exploreCategoryState.value = ExploreCategoryState.PLANETS
            }
            ExploreCategories.moons -> {
                _exploreCategoryState.value = ExploreCategoryState.MOONS
            }
            ExploreCategories.comets -> {
                _exploreCategoryState.value = ExploreCategoryState.COMETS
            }
            ExploreCategories.stars -> {
                _exploreCategoryState.value = ExploreCategoryState.STARS
            }
        }
    }

    fun getExploreGalaxyData(applicationContext: Context) =
        viewModelScope.launch(Dispatchers.IO) {
            getExploreGalaxyDataUseCase(applicationContext = applicationContext).collect() { result ->
                when (result) {
                    is Result.Loading -> {
                        _exploreGalaxyState.value = ExploreGalaxyState.Loading
                    }
                    is Result.Success -> {
                        _exploreGalaxyState.value = ExploreGalaxyState.Success(data = result.data)
                    }
                    is Result.Error -> {
                        _exploreGalaxyState.value = ExploreGalaxyState.Error(errorMessage = result.message)
                    }
                }
            }
        }

    fun getApodFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        getApodFromNetworkUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _apodState.value = ApodState.Loading
                }
                is Result.Success -> {
                    _apodState.value = ApodState.Success(apodData = result.data)
                    if (result.data != null) {
                        clearLocalApodUseCase()
                        addApodToDatabaseUseCase(apod = result.data)
                    }
                }
                is Result.Error -> {
                    getApodFromLocal()
                }
            }
        }
    }

    fun getApodFromLocal() = viewModelScope.launch(Dispatchers.IO) {
        getApodFromLocalUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _apodState.value = ApodState.Loading
                }
                is Result.Success -> {
                    _apodState.value = ApodState.Success(apodData = result.data)
                }
                is Result.Error -> {
                    _apodState.value = ApodState.Error(errorMessage = result.message)
                }
            }
        }
    }
}