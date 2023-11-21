package com.spaceapp.presentation.profile.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.huawei.agconnect.auth.AGConnectAuth
import com.spaceapp.R
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.profile.ProfileViewModel

@Composable
fun LogOutSection(navController: NavController, viewModel: ProfileViewModel) {
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