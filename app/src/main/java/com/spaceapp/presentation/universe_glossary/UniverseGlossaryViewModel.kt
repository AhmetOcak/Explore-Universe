package com.spaceapp.presentation.universe_glossary

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.Response
import com.spaceapp.domain.model.glossary_data.Glossary
import com.spaceapp.domain.usecase.glossary.GetGlossaryUseCase
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
class UniverseGlossaryViewModel @Inject constructor(
    private val getGlossaryUseCase: GetGlossaryUseCase,
    @ApplicationContext applicationContext: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var search by mutableStateOf("")
        private set

    init {
        getGlossary(applicationContext = applicationContext)
    }

    private fun getGlossary(applicationContext: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            getGlossaryUseCase(applicationContext = applicationContext).collect { result ->
                when(result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(glossaryState = GlossaryState.Loading)
                        }
                    }
                    is Response.Success -> {
                        _uiState.update {
                            it.copy(glossaryState = GlossaryState.Success(data = result.data))
                        }
                    }
                    is Response.Error -> {
                        _uiState.update {
                            it.copy(glossaryState = GlossaryState.Error(errorMessage = result.message))
                        }
                    }
                }
            }
        }
    }

    fun updateSearchField(newValue: String) {
        search = newValue
    }
}

data class UiState(
    val glossaryState: GlossaryState = GlossaryState.Loading
)

sealed interface GlossaryState {
    data class Success(val data: Glossary?) : GlossaryState
    data class Error(val errorMessage: String?) : GlossaryState
    data object Loading : GlossaryState
}