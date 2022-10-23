package com.spaceapp.presentation.universe_glossary

import com.spaceapp.domain.model.Glossary

sealed interface GlossaryState {
    data class Success(val data: Glossary?) : GlossaryState
    data class Error(val errorMessage: String?) : GlossaryState
    object Loading : GlossaryState
}