package com.spaceapp.presentation.universe_glossary.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.DefaultOutlinedTextField
import com.spaceapp.presentation.universe_glossary.UniverseGlossaryViewModel
import com.spaceapp.presentation.utils.UniverseGlossaryScreenConstants

@Composable
fun SearchField(modifier: Modifier, viewModel: UniverseGlossaryViewModel) {
    DefaultOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        onValueChanged = { viewModel.updateSearchField(newValue = it) },
        labelText = UniverseGlossaryScreenConstants.search_field_text,
        keyboardType = KeyboardType.Text,
        leadingIconId = R.drawable.ic_baseline_search,
        value = viewModel.search
    )
}