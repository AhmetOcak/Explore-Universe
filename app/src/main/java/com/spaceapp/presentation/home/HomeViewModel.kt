package com.spaceapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Result
import com.spaceapp.domain.usecase.mars_photo.*
import com.spaceapp.domain.usecase.people_in_space.*
import com.spaceapp.domain.usecase.where_is_the_iss.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestMarsPhotoFromNetworkUseCase: GetLatestMarsPhotoFromNetworkUseCase,
    private val getMarsPhotoFromDatabaseUseCase: GetMarsPhotoFromDatabaseUseCase,
    private val addMarsPhotoToDatabaseUseCase: AddMarsPhotoToDatabaseUseCase,
    private val clearMarsPhotoDatabaseUseCase: ClearMarsPhotoDatabaseUseCase,
    private val getIssPositionFromDatabaseUseCase: GetIssPositionFromDatabaseUseCase,
    private val getIssPositionFromNetworkUseCase: GetIssPositionFromNetworkUseCase,
    private val addIssPositionToDatabaseUseCase: AddIssPositionToDatabaseUseCase,
    private val updateIssPositionUseCase: UpdateIssPositionUseCase,
    private val getPeopleInSpaceFromDatabaseUseCase: GetPeopleInSpaceFromDatabaseUseCase,
    private val getPeopleInSpaceRightNowUseCase: GetPeopleInSpaceRightNowUseCase,
    private val addPeopleInSpaceToDatabaseUseCase: AddPeopleInSpaceToDatabaseUseCase,
    private val clearPeopleInSpaceDatabaseUseCase: ClearPeopleInSpaceDatabaseUseCase,
) : ViewModel() {

    private val _marsPhotoState = MutableStateFlow<MarsPhotoState>(MarsPhotoState.Loading)
    val marsPhotoState = _marsPhotoState.asStateFlow()

    private val _whereIsTheIssState = MutableStateFlow<WhereIsTheIssState>(WhereIsTheIssState.Loading)
    val whereIsTheIssState = _whereIsTheIssState.asStateFlow()

    private val _peopleInSpaceState = MutableStateFlow<PeopleInSpaceState>(PeopleInSpaceState.Loading)
    val peopleInSpaceState = _peopleInSpaceState.asStateFlow()

    var isMarsPhotoDataTakenFromDatabase by mutableStateOf(false)
        private set

    var isPeopleInSpaceDataTakenFromDatabase by mutableStateOf(false)
        private set

    fun getLatestMarsPhotosFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        getLatestMarsPhotoFromNetworkUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _marsPhotoState.value = MarsPhotoState.Loading
                }
                is Result.Success -> {
                    isMarsPhotoDataTakenFromDatabase = false
                    _marsPhotoState.value = MarsPhotoState.Success(data = result.data)
                    if (!result.data.isNullOrEmpty()) {
                        clearMarsPhotoDatabaseUseCase()
                        addMarsPhotoToDatabaseUseCase(marsPhoto = result.data[0])
                    }
                }
                is Result.Error -> {
                    getMarsPhotosFromDatabase()
                }
            }
        }
    }

    private fun getMarsPhotosFromDatabase() = viewModelScope.launch(Dispatchers.IO) {
        getMarsPhotoFromDatabaseUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _marsPhotoState.value = MarsPhotoState.Loading
                }
                is Result.Success -> {
                    isMarsPhotoDataTakenFromDatabase = true
                    _marsPhotoState.value = MarsPhotoState.Success(data = result.data)
                }
                is Result.Error -> {
                    _marsPhotoState.value = MarsPhotoState.Error(errorMessage = result.message)
                }
            }
        }
    }

    fun getIssPositionFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        getIssPositionFromNetworkUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _whereIsTheIssState.value = WhereIsTheIssState.Loading
                }
                is Result.Success -> {
                    _whereIsTheIssState.value = WhereIsTheIssState.Success(data = result.data)
                    if (result.data != null) {
                        getIssPositionFromDatabaseUseCase().collect() {
                            when (it) {
                                is Result.Success -> {
                                    updateIssPositionUseCase(iss = result.data)
                                }
                                is Result.Error -> {
                                    addIssPositionToDatabaseUseCase(iss = result.data)
                                }
                                else -> {}
                            }
                        }
                    }
                }
                is Result.Error -> {
                    getIssPositionFromDatabase()
                }
            }
        }
    }

    private fun getIssPositionFromDatabase() = viewModelScope.launch(Dispatchers.IO) {
        getIssPositionFromDatabaseUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _whereIsTheIssState.value = WhereIsTheIssState.Loading
                }
                is Result.Success -> {
                    _whereIsTheIssState.value = WhereIsTheIssState.Success(data = result.data)
                }
                is Result.Error -> {
                    _whereIsTheIssState.value =
                        WhereIsTheIssState.Error(errorMessage = result.message)
                }
            }
        }
    }

    fun getPeopleInSpaceFromNetwork() = viewModelScope.launch(Dispatchers.IO) {
        getPeopleInSpaceRightNowUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _peopleInSpaceState.value = PeopleInSpaceState.Loading
                }
                is Result.Success -> {
                    isPeopleInSpaceDataTakenFromDatabase = false
                    _peopleInSpaceState.value = PeopleInSpaceState.Success(data = result.data)
                    if (!result.data.isNullOrEmpty()) {
                        clearPeopleInSpaceDatabaseUseCase()
                        addPeopleInSpaceToDatabaseUseCase(peopleInSpace = result.data[0])
                    }
                }
                is Result.Error -> {
                    getPeopleInSpaceFromDatabase()
                }
            }
        }
    }

    private fun getPeopleInSpaceFromDatabase() = viewModelScope.launch(Dispatchers.IO) {
        getPeopleInSpaceFromDatabaseUseCase().collect() { result ->
            when (result) {
                is Result.Loading -> {
                    _peopleInSpaceState.value = PeopleInSpaceState.Loading
                }
                is Result.Success -> {
                    isPeopleInSpaceDataTakenFromDatabase = true
                    _peopleInSpaceState.value = PeopleInSpaceState.Success(data = result.data)
                }
                is Result.Error -> {
                    _peopleInSpaceState.value = PeopleInSpaceState.Error(errorMessage = result.message)
                }
            }
        }
    }

    fun loadAllData() {
        getLatestMarsPhotosFromNetwork()
        getIssPositionFromNetwork()
        getPeopleInSpaceFromNetwork()
    }

    // created for splash screen
    fun isDataReady(): Boolean {
        return _marsPhotoState.value != MarsPhotoState.Loading &&
                _whereIsTheIssState.value != WhereIsTheIssState.Loading &&
                _peopleInSpaceState.value != PeopleInSpaceState.Loading
    }
}