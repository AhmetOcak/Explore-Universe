package com.spaceapp.presentation.profile

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import com.spaceapp.R
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.core.ui.component.BackgroundImage
import com.spaceapp.core.ui.component.Gif

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ProfileScreenContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: ProfileViewModel
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
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
}

@Composable
private fun LogOutSection(navController: NavController, viewModel: ProfileViewModel) {
    IconButton(
        onClick = {
            navController.navigate(NavScreen.LoginScreen.route) {
                popUpTo(0)
            }
            if (viewModel.currentUserGms != null) {
                FirebaseAuth.getInstance().signOut()
            } else {
                AGConnectAuth.getInstance().signOut()
            }
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_logout),
            contentDescription = null
        )
    }
}

@Composable
private fun ProfilePreviewSection(modifier: Modifier, viewModel: ProfileViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = LocalConfiguration.current.screenHeightDp.dp / 4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UserProfileImage(
            modifier = modifier
        )
        UserProfileName(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}

@Composable
private fun UserProfileImage(modifier: Modifier) {
    Gif(modifier = modifier.clip(CircleShape), gifId = R.drawable.profile)
}

@Composable
private fun UserProfileName(modifier: Modifier, viewModel: ProfileViewModel) {
    Text(
        modifier = modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
        text = if (viewModel.currentUserGms != null) viewModel.currentUserGms.email!! else viewModel.currentUserHms?.email!!,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}