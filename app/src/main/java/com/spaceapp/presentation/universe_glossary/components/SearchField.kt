package com.spaceapp.presentation.universe_glossary.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.DefaultOutlinedTextField
import com.spaceapp.presentation.utils.UniverseGlossaryScreenConstants

@Composable
fun SearchField(modifier: Modifier, searchValue: String, onSearchValChange: (String) -> Unit) {
    DefaultOutlinedTextField(
        modifier = modifier,
        onValueChanged = onSearchValChange,
        labelText = UniverseGlossaryScreenConstants.search_field_text,
        keyboardType = KeyboardType.Text,
        leadingIconId = R.drawable.ic_baseline_search,
        value = searchValue
    )
}