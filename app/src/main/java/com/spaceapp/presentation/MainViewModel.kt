package com.spaceapp.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(consumableUiEvents = listOf(UiEvent.INIT))
        }
    }

    fun consumedUiEvent() {
        _uiState.update {
            it.copy(consumableUiEvents = listOf())
        }
    }
}

data class UiState(
    val consumableUiEvents: List<UiEvent> = listOf()
)

sealed interface UiEvent {
    data object INIT : UiEvent
}