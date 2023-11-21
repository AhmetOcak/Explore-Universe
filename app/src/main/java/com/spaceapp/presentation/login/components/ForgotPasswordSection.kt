package com.spaceapp.presentation.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.utils.LoginScreenConstants

@Composable
fun ForgotPasswordSection(modifier: Modifier, navController: NavController) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        TextButton(
            onClick = {
                navController.navigate(NavScreen.ForgotPasswordScreen.route)
            }
        ) {
            Text(
                text = LoginScreenConstants.forgot_password,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h4
            )
        }
    }
}