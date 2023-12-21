package com.spaceapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Response
import com.spaceapp.domain.usecase.mars_photo.*
import com.spaceapp.domain.usecase.people_in_space.*
import com.spaceapp.domain.usecase.where_is_the_iss.*
import com.spaceapp.presentation.home.state.MarsPhotoState
import com.spaceapp.presentation.home.state.PeopleInSpaceState
import com.spaceapp.presentation.home.state.IssState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var isMarsPhotoDataTakenFromDatabase by mutableStateOf(false)
        private set

    var isPeopleInSpaceDataTakenFromDatabase by mutableStateOf(false)
        private set

    fun getLatestMarsPhotosFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getLatestMarsPhotoFromNetworkUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(marsPhotoState = MarsPhotoState.Loading)
                        }
                    }

                    is Response.Success -> {
                        isMarsPhotoDataTakenFromDatabase = false
                        _uiState.update {
                            it.copy(marsPhotoState = MarsPhotoState.Success(data = result.data))
                        }
                        if (!result.data.isNullOrEmpty()) {
                            clearMarsPhotoDatabaseUseCase()
                            addMarsPhotoToDatabaseUseCase(marsPhoto = result.data[0])
                        }
                    }

                    is Response.Error -> {
                        getMarsPhotosFromDatabase()
                    }
                }
            }
        }
    }

    private fun getMarsPhotosFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getMarsPhotoFromDatabaseUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(marsPhotoState = MarsPhotoState.Loading)
                        }
                    }

                    is Response.Success -> {
                        isMarsPhotoDataTakenFromDatabase = true
                        _uiState.update {
                            it.copy(marsPhotoState = MarsPhotoState.Success(data = result.data))
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(marsPhotoState = MarsPhotoState.Error(errorMessage = result.message))
                        }
                    }
                }
            }
        }
    }

    fun getIssPositionFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getIssPositionFromNetworkUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(issState = IssState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(issState = IssState.Success(data = result.data))
                        }
                        if (result.data != null) {
                            getIssPositionFromDatabaseUseCase().collect {
                                when (it) {
                                    is Response.Success -> {
                                        updateIssPositionUseCase(iss = result.data)
                                    }

                                    is Response.Error -> {
                                        addIssPositionToDatabaseUseCase(iss = result.data)
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }

                    is Response.Error -> {
                        getIssPositionFromDatabase()
                    }
                }
            }
        }
    }

    private fun getIssPositionFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getIssPositionFromDatabaseUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(issState = IssState.Loading)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(issState = IssState.Success(data = result.data))
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(issState = IssState.Error(errorMessage = result.message))
                        }
                    }
                }
            }
        }
    }

    fun getPeopleInSpaceFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleInSpaceRightNowUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(peopleInSpace = PeopleInSpaceState.Loading)
                        }
                    }

                    is Response.Success -> {
                        isPeopleInSpaceDataTakenFromDatabase = false
                        _uiState.update {
                            it.copy(peopleInSpace = PeopleInSpaceState.Success(data = result.data))
                        }
                        if (!result.data.isNullOrEmpty()) {
                            clearPeopleInSpaceDatabaseUseCase()
                            addPeopleInSpaceToDatabaseUseCase(peopleInSpace = result.data[0])
                        }
                    }

                    is Response.Error -> {
                        getPeopleInSpaceFromDatabase()
                    }
                }
            }
        }
    }

    private fun getPeopleInSpaceFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getPeopleInSpaceFromDatabaseUseCase().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(peopleInSpace = PeopleInSpaceState.Loading)
                        }
                    }

                    is Response.Success -> {
                        isPeopleInSpaceDataTakenFromDatabase = true
                        _uiState.update {
                            it.copy(peopleInSpace = PeopleInSpaceState.Success(data = result.data))
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(peopleInSpace = PeopleInSpaceState.Error(errorMessage = result.message))
                        }
                    }
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
        return _uiState.value.marsPhotoState != MarsPhotoState.Loading &&
                _uiState.value.issState != IssState.Loading &&
                _uiState.value.peopleInSpace != PeopleInSpaceState.Loading
    }
}

data class UiState(
    val marsPhotoState: MarsPhotoState = MarsPhotoState.Loading,
    val peopleInSpace: PeopleInSpaceState = PeopleInSpaceState.Loading,
    val issState: IssState = IssState.Loading
)