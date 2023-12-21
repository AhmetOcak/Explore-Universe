package com.spaceapp.presentation.universe_glossary

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Result
import com.spaceapp.domain.usecase.glossary.GetGlossaryUseCase
import com.spaceapp.presentation.universe_glossary.state.GlossaryState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniverseGlossaryViewModel @Inject constructor(
    private val getGlossaryUseCase: GetGlossaryUseCase,
    @ApplicationContext applicationContext: Context
) : ViewModel() {

    private val _glossaryState = MutableStateFlow<GlossaryState>(GlossaryState.Loading)
    val glossaryState = _glossaryState.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getGlossary(applicationContext = applicationContext)
    }

    private fun getGlossary(applicationContext: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            getGlossaryUseCase(applicationContext = applicationContext).collect { result ->
                when(result) {
                    is Result.Loading -> {
                        _glossaryState.value = GlossaryState.Loading
                    }
                    is Result.Success -> {
                        _glossaryState.value = GlossaryState.Success(data = result.data)
                    }
                    is Result.Error -> {
                        _glossaryState.value = GlossaryState.Error(errorMessage = result.message)
                    }
                }
            }
        }
    }

    fun updateSearchField(newValue: String) {
        search = newValue
    }
}