package com.spaceapp.presentation.explore_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spaceapp.presentation.utils.ExploreDetailCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _categoryState = MutableStateFlow<CategoryState>(CategoryState.Overview)
    val categoryState = _categoryState.asStateFlow()

    val name = savedStateHandle.get<String>("name")
    val description = savedStateHandle.get<String>("description")
    val info1 = savedStateHandle.get<String>("info1")
    val info2 = savedStateHandle.get<String>("info2")

    fun categoryOnClick(categoryName: String) {
        if (categoryName == ExploreDetailCategories.information) {
            _categoryState.value = CategoryState.Information
        } else {
            _categoryState.value = CategoryState.Overview
        }
    }
}
