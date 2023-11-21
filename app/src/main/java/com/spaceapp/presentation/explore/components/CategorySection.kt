package com.spaceapp.presentation.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spaceapp.core.designsystem.component.DefaultTextButton
import com.spaceapp.presentation.explore.ExploreViewModel
import com.spaceapp.presentation.utils.ExploreCategories

private val categories = listOf(
    ExploreCategories.all,
    ExploreCategories.planets,
    ExploreCategories.moons,
    ExploreCategories.meteors,
    ExploreCategories.comets,
    ExploreCategories.stars,
)

@Composable
fun CategorySection(modifier: Modifier = Modifier, viewModel: ExploreViewModel) {
    var selected by rememberSaveable { mutableStateOf(0) }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categories) { index, item ->
            DefaultTextButton(
                category = item,
                index = index,
                onClick = {
                    selected = index
                    viewModel.exploreCategoryOnClick(categoryName = item)
                },
                selected = selected
            )
        }
    }
}