package com.spaceapp.presentation.home

import android.app.Activity
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
        marsPhotoComingFromDb = viewModel.isMarsPhotoDataTakenFromDatabase,
        peopleComingFromDb = viewModel.isPeopleInSpaceDataTakenFromDatabase,
        retryAstronautsInfo = viewModel::getPeopleInSpaceFromNetwork,
        retryMarsPhotoData = viewModel::getLatestMarsPhotosFromNetwork,
        retryIssPositionInfo = viewModel::getIssPositionFromNetwork
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    marsPhotoState: MarsPhotoState,
    whereIsTheIssState: WhereIsTheIssState,
    peopleInSpaceState: PeopleInSpaceState,
    marsPhotoComingFromDb: Boolean,
    peopleComingFromDb: Boolean,
    retryAstronautsInfo: () -> Unit,
    retryMarsPhotoData: () -> Unit,
    retryIssPositionInfo: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.Start
    ) {
        WordOfCarlSagan()
        WhereIsTheIssSection(
            whereIsTheIssState = whereIsTheIssState,
            retryIssPositionInfo = retryIssPositionInfo
        )
        PeopleInSpaceRightNowSection(
            peopleInSpaceState = peopleInSpaceState,
            dataComingFromDb = peopleComingFromDb,
            retryAstronautsInfo = retryAstronautsInfo
        )
        MarsPhotosSection(
            marsPhotoState = marsPhotoState,
            dataComingFromDb = marsPhotoComingFromDb,
            retryMarsPhotoData = retryMarsPhotoData
        )
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}