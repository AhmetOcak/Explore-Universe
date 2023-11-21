package com.spaceapp.presentation.universe_glossary

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.*
import com.spaceapp.domain.model.glossary_data.GlossaryContent
import com.spaceapp.presentation.universe_glossary.components.SearchField
import com.spaceapp.presentation.universe_glossary.components.SearchResultEmpty
import com.spaceapp.presentation.universe_glossary.components.StatefulGlossaryCard
import com.spaceapp.presentation.universe_glossary.state.GlossaryState
import com.spaceapp.presentation.utils.GlossaryImageType
import com.spaceapp.presentation.utils.UniverseGlossaryScreenConstants

private val constants = UniverseGlossaryScreenConstants

@Composable
fun UniverseGlossaryScreen(
    modifier: Modifier = Modifier,
    viewModel: UniverseGlossaryViewModel = hiltViewModel()
) {
    val glossaryState by viewModel.glossaryState.collectAsState()

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    UniverseGlossaryContent(
        modifier = modifier,
        glossaryState = glossaryState,
        viewModel = viewModel
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun UniverseGlossaryContent(
    modifier: Modifier,
    glossaryState: GlossaryState,
    viewModel: UniverseGlossaryViewModel
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        BackgroundImage(modifier = modifier.fillMaxSize(), imageId = R.drawable.background_image)
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                text = constants.title,
                style = MaterialTheme.typography.h1
            )
            SearchField(
                modifier = modifier,
                viewModel = viewModel
            )
            when (glossaryState) {
                is GlossaryState.Loading -> {
                    LoadingSpinner(modifier = modifier.fillMaxSize())
                }
                is GlossaryState.Success -> {
                    glossaryState.data?.glossary?.let { list ->
                        TermList(
                            modifier = modifier,
                            filteredGlossaryList = list.filter {
                                it.name.contains(
                                    viewModel.search,
                                    ignoreCase = true
                                )
                            }
                        )
                    }
                }
                is GlossaryState.Error -> {
                    ErrorCard(errorDescription = glossaryState.errorMessage.toString())
                }
            }
        }
    }
}

@Composable
private fun TermList(
    modifier: Modifier,
    filteredGlossaryList: List<GlossaryContent>
) {
    if (filteredGlossaryList.isEmpty()) {
        SearchResultEmpty(modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = filteredGlossaryList, key = { it.name }) {
                StatefulGlossaryCard(
                    title = it.name,
                    description = it.description,
                    imageId = GlossaryImageType.setGlossaryImageType(it.name)
                )
            }
        }
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}