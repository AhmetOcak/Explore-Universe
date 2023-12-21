package com.spaceapp.presentation.explore_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spaceapp.presentation.utils.ExploreDetailCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ExploreDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val name = savedStateHandle.get<String>("name")
    val description = savedStateHandle.get<String>("description")
    val info1 = savedStateHandle.get<String>("info1")
    val info2 = savedStateHandle.get<String>("info2")

    fun categoryOnClick(categoryName: String) {
        if (categoryName == ExploreDetailCategories.information) {
            _uiState.update {
                it.copy(categoryState = CategoryState.Information)
            }
        } else {
            _uiState.update {
                it.copy(categoryState = CategoryState.Overview)
            }
        }
    }
}

data class UiState(
    val categoryState: CategoryState = CategoryState.Overview
)

sealed interface CategoryState {
    data object Overview : CategoryState
    data object Information : CategoryState
}