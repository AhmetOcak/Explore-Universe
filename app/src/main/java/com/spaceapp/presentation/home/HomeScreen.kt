package com.spaceapp.presentation.home

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.*
import com.spaceapp.presentation.home.components.MarsPhotosSection
import com.spaceapp.presentation.home.components.PeopleInSpaceRightNowSection
import com.spaceapp.presentation.home.components.WhereIsTheIssSection
import com.spaceapp.presentation.home.components.WordOfCarlSagan
import com.spaceapp.presentation.home.state.MarsPhotoState
import com.spaceapp.presentation.home.state.PeopleInSpaceState
import com.spaceapp.presentation.home.state.WhereIsTheIssState

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val marsPhotoState by viewModel.marsPhotoState.collectAsState()
    val whereIsTheIssState by viewModel.whereIsTheIssState.collectAsState()
    val peopleInSpaceState by viewModel.peopleInSpaceState.collectAsState()

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    HomeScreenContent(
        modifier = modifier,
        marsPhotoState = marsPhotoState,
        whereIsTheIssState = whereIsTheIssState,
        peopleInSpaceState = peopleInSpaceState,
        context = LocalContext.current,
        marsPhotoComingFromDb = viewModel.isMarsPhotoDataTakenFromDatabase,
        peopleComingFromDb = viewModel.isPeopleInSpaceDataTakenFromDatabase,
        viewModel = viewModel
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    marsPhotoState: MarsPhotoState,
    whereIsTheIssState: WhereIsTheIssState,
    peopleInSpaceState: PeopleInSpaceState,
    context: Context,
    marsPhotoComingFromDb: Boolean,
    peopleComingFromDb: Boolean,
    viewModel: HomeViewModel
) {
    BackgroundImage(modifier = modifier.fillMaxSize(), imageId = R.drawable.background_image)
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.Start
    ) {
        WordOfCarlSagan(modifier = modifier)
        WhereIsTheIssSection(
            modifier = modifier,
            whereIsTheIssState = whereIsTheIssState,
            viewModel = viewModel
        )
        PeopleInSpaceRightNowSection(
            modifier = modifier,
            peopleInSpaceState = peopleInSpaceState,
            dataComingFromDb = peopleComingFromDb,
            viewModel = viewModel
        )
        MarsPhotosSection(
            modifier = modifier,
            marsPhotoState = marsPhotoState,
            context = context,
            dataComingFromDb = marsPhotoComingFromDb,
            marsPhotoErrorOnClick = { viewModel.getLatestMarsPhotosFromNetwork() }
        )
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}