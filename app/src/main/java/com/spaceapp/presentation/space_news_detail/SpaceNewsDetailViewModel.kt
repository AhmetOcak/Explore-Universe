package com.spaceapp.presentation.space_news_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SpaceNewsDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    val newsUrl = savedStateHandle.get<String>("newsUrl")
}