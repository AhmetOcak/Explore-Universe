package com.spaceapp.presentation.profile

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spaceapp.R
import com.spaceapp.core.designsystem.components.Gif
import com.spaceapp.core.designsystem.theme.BlueHaze

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogOutClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    ProfileScreenContent(
        modifier = modifier,
        onLogOutClick = {
            viewModel.logOut()
            onLogOutClick()
        },
        profileName = uiState.profileName ?: "Space Traveler"
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    onLogOutClick: () -> Unit,
    profileName: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.End
    ) {
        IconButton(onClick = onLogOutClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_logout),
                contentDescription = null,
                tint = BlueHaze
            )
        }
        ProfilePreviewSection(profileName = profileName)
    }
}

@Composable
fun ProfilePreviewSection(profileName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = LocalConfiguration.current.screenHeightDp.dp / 4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UserProfileImage(modifier = Modifier.clip(CircleShape))
        UserProfileName(
            modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
            profileName = profileName
        )
    }
}

@Composable
private fun UserProfileImage(modifier: Modifier) {
    Gif(modifier = modifier, gifId = R.drawable.profile)
}

@Composable
private fun UserProfileName(modifier: Modifier, profileName: String) {
    Text(
        modifier = modifier,
        text = profileName,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}