package com.spaceapp.presentation.explore

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.core.designsystem.components.DefaultTextButton
import com.spaceapp.core.designsystem.components.ErrorCard
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.presentation.explore.components.ApodCard
import com.spaceapp.presentation.explore.components.ExploreCardSection
import com.spaceapp.presentation.explore.state.*
import com.spaceapp.presentation.utils.ExploreCategories
import com.spaceapp.presentation.utils.ExploreScreenConstants

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    ExploreScreenContent(
        modifier = modifier,
        apodState = uiState.apodState,
        exploreGalaxyState = uiState.exploreGalaxyState,
        exploreCategoryState = uiState.exploreCategoryState,
        navController = navController,
        onCategoryClick = {
            viewModel.exploreCategoryOnClick(it)
        }
    )
}

@Composable
private fun ExploreScreenContent(
    modifier: Modifier,
    apodState: ApodState,
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    navController: NavController,
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        PageTitle()
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            ExploreSection(
                exploreGalaxyState = exploreGalaxyState,
                exploreCategoryState = exploreCategoryState,
                navController = navController,
                onCategoryClick = onCategoryClick
            )
            UniverseImageSection(apodState = apodState)
        }
    }
}

@Composable
private fun PageTitle() {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = ExploreScreenConstants.title_1,
        style = MaterialTheme.typography.headlineLarge
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        thickness = 1.dp
    )
}

@Composable
private fun ExploreSection(
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    navController: NavController,
    onCategoryClick: (String) -> Unit
) {
    CategorySection(onCategoryClick = onCategoryClick)
    ExploreCardSection(
        exploreGalaxyState = exploreGalaxyState,
        exploreCategoryState = exploreCategoryState,
        navController = navController
    )
}

@Composable
fun UniverseImageSection(apodState: ApodState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp),
            text = ExploreScreenConstants.title_2,
            style = MaterialTheme.typography.headlineMedium
        )
        when (apodState) {
            is ApodState.Loading -> {
                LoadingSpinner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                )
            }
            is ApodState.Success -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(apodState.apodData!!) {
                        ApodCard(
                            title = it.title,
                            context = LocalContext.current,
                            imageUrl = it.image,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            is ApodState.Error -> {
                ErrorCard(errorDescription = apodState.errorMessage.toString())
            }
        }
    }
}

@Composable
private fun CategorySection(onCategoryClick: (String) -> Unit) {
    var selected by rememberSaveable { mutableIntStateOf(0) }

    LazyRow(
        modifier = Modifier
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
                    onCategoryClick(item)
                },
                selected = selected
            )
        }
    }
}

private val categories = listOf(
    ExploreCategories.all,
    ExploreCategories.planets,
    ExploreCategories.moons,
    ExploreCategories.meteors,
    ExploreCategories.comets,
    ExploreCategories.stars,
)

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}