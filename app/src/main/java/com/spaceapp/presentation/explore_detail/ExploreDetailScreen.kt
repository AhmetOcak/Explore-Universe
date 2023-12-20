package com.spaceapp.presentation.explore_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spaceapp.core.designsystem.component.DefaultTextButton
import com.spaceapp.presentation.explore_detail.components.OverViewSection
import com.spaceapp.presentation.explore_detail.state.CategoryState
import com.spaceapp.presentation.utils.*

@Composable
fun ExploreDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreDetailViewModel = hiltViewModel()
) {
    val categoryState by viewModel.categoryState.collectAsState()

    ExploreDetailScreenContent(
        modifier = modifier,
        viewModel = viewModel,
        categoryState = categoryState,
        objectName = viewModel.name,
        objectInfo1 = viewModel.info1,
        objectInfo2 = viewModel.info2,
        objectDescription = viewModel.description
    )
}

@Composable
private fun ExploreDetailScreenContent(
    modifier: Modifier,
    viewModel: ExploreDetailViewModel,
    categoryState: CategoryState,
    objectName: String?,
    objectInfo1: String?,
    objectInfo2: String?,
    objectDescription: String?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CategoriesSection(modifier = modifier.fillMaxWidth(), viewModel = viewModel)
        when (categoryState) {
            is CategoryState.Overview -> {
                OverViewSection(
                    objectName = objectName,
                    objectInfo1 = objectInfo1,
                    objectInfo2 = objectInfo2
                )
            }

            is CategoryState.Information -> {
                InformationSection(objectName = objectName, objectDescription = objectDescription)
            }
        }
    }
}

@Composable
private fun CategoriesSection(modifier: Modifier, viewModel: ExploreDetailViewModel) {
    var selected by rememberSaveable { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        itemsIndexed(categories) { index, item ->
            DefaultTextButton(
                category = item,
                index = index,
                onClick = {
                    selected = index
                    viewModel.categoryOnClick(categoryName = item)
                },
                selected = selected
            )
        }
    }
}

@Composable
private fun InformationSection(objectName: String?, objectDescription: String?) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(15)),
        painter = painterResource(id = SpaceObjectImageType.setSpaceObjectImageType(objectName!!)),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = objectName,
        style = MaterialTheme.typography.headlineLarge
    )
    Text(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        text = objectDescription ?: "",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
}

private val categories = listOf(
    ExploreDetailCategories.overview,
    ExploreDetailCategories.information
)