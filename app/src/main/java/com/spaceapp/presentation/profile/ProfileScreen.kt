package com.spaceapp.presentation.profile

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.BackgroundImage
import com.spaceapp.presentation.profile.components.LogOutSection
import com.spaceapp.presentation.profile.components.ProfilePreviewSection

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    ProfileScreenContent(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: ProfileViewModel
) {
    BackgroundImage(modifier = modifier.fillMaxSize(), imageId = R.drawable.background_image)
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.End
    ) {
        LogOutSection(
            navController = navController,
            viewModel = viewModel
        )
        ProfilePreviewSection(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}