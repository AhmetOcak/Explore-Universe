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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spaceapp.core.designsystem.components.DefaultTextButton
import com.spaceapp.presentation.utils.*

@Composable
fun ExploreDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ExploreDetailScreenContent(
        modifier = modifier,
        viewModel = viewModel,
        categoryState = uiState.categoryState,
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

@Composable
fun OverViewSection(
    objectName: String?,
    objectInfo1: String?,
    objectInfo2: String?
) {
    objectName?.let { SpaceObjectImageType.setSpaceObjectImageType(it) }?.let {
        OverView(
            title1 = "radius",
            info1 = if (objectInfo1 == NoData.noData) NoData.noData else "r = ${objectInfo1}km",
            title2 = if (isMeteor(objectName)) "velocity" else "distance from sun",
            info2 = if (isMeteor(objectName)) "${objectInfo2}km/s" else "${objectInfo2}km",
            objectName = objectName,
            objectImage = it
        )
    }
}

@Composable
private fun OverView(
    title1: String,
    info1: String,
    title2: String,
    info2: String,
    objectName: String,
    objectImage: Int,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(15)),
        painter = painterResource(id = objectImage),
        contentDescription = null,
        contentScale = contentScale
    )
    Text(
        modifier = Modifier.padding(bottom = 32.dp),
        text = objectName,
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 56.sp),
        textAlign = TextAlign.Center
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title1,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = info1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = title2, style = MaterialTheme.typography.bodySmall)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = info2,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun isMeteor(name: String): Boolean {
    return when (name) {
        SpaceObjects.lenoids -> { true }
        SpaceObjects.lyrids -> { true }
        SpaceObjects.orinoids -> { true }
        SpaceObjects.perseids -> { true }
        else -> { false }
    }
}

private val categories = listOf(
    ExploreDetailCategories.overview,
    ExploreDetailCategories.information
)