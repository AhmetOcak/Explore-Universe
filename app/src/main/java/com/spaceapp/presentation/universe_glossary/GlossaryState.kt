package com.spaceapp.presentation.universe_glossary

import com.spaceapp.domain.model.glossary_data.Glossary

sealed interface GlossaryState {
    data class Success(val data: Glossary?) : GlossaryState
    data class Error(val errorMessage: String?) : GlossaryState
    object Loading : GlossaryState
}