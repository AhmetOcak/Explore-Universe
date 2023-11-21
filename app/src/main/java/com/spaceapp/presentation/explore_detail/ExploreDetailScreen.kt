package com.spaceapp.presentation.explore_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.BackgroundImage
import com.spaceapp.core.designsystem.component.DefaultTextButton
import com.spaceapp.presentation.explore_detail.components.OverView
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
        categoryState = categoryState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ExploreDetailScreenContent(
    modifier: Modifier,
    viewModel: ExploreDetailViewModel,
    categoryState: CategoryState,
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        BackgroundImage(modifier = modifier.fillMaxSize(), imageId = R.drawable.background_image)
        Column(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CategoriesSection(modifier = modifier, viewModel = viewModel)
            when (categoryState) {
                is CategoryState.Overview -> {
                    OverViewSection(modifier = modifier, viewModel = viewModel)
                }
                is CategoryState.Information -> {
                    InformationSection(modifier = modifier, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
private fun CategoriesSection(modifier: Modifier, viewModel: ExploreDetailViewModel) {
    var selected by rememberSaveable { mutableStateOf(0) }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
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
fun OverViewSection(modifier: Modifier, viewModel: ExploreDetailViewModel) {
    viewModel.name?.let { SpaceObjectImageType.setSpaceObjectImageType(it) }?.let {
        OverView(
            modifier = modifier,
            title1 = "radius",
            info1 = if (viewModel.info1 == NoData.noData) NoData.noData else "r = ${viewModel.info1}km",
            title2 = if (isMeteor(viewModel.name)) "velocity" else "distance from sun",
            info2 = if (isMeteor(viewModel.name)) "${viewModel.info2}km/s" else "${viewModel.info2}km",
            objectName = viewModel.name,
            objectImage = it
        )
    }
}

@Composable
private fun InformationSection(modifier: Modifier, viewModel: ExploreDetailViewModel) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(15)),
        painter = painterResource(id = SpaceObjectImageType.setSpaceObjectImageType(viewModel.name!!)),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
    Text(
        modifier = modifier.padding(vertical = 16.dp),
        text = viewModel.name,
        style = MaterialTheme.typography.h1
    )
    Text(
        modifier = modifier.verticalScroll(rememberScrollState()),
        text = viewModel.description ?: "",
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center
    )
}

private val categories = listOf(
    ExploreDetailCategories.overview,
    ExploreDetailCategories.information
)

private fun isMeteor(name: String): Boolean {
    return when (name) {
        SpaceObjects.lenoids -> {
            true
        }
        SpaceObjects.lyrids -> {
            true
        }
        SpaceObjects.orinoids -> {
            true
        }
        SpaceObjects.perseids -> {
            true
        }
        else -> {
            false
        }
    }
}