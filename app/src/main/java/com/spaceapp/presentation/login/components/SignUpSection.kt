package com.spaceapp.presentation.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.login.LoginViewModel
import com.spaceapp.presentation.utils.LoginScreenConstants

@Composable
fun SignUpSection(
    modifier: Modifier,
    navController: NavController,
    viewModel: LoginViewModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = LoginScreenConstants.no_account_text,
            fontSize = 16.sp
        )
        TextButton(
            onClick = {
                viewModel.resetLoginInputFieldState()
                navController.navigate(NavScreen.SignUpScreen.route)
            }
        ) {
            Text(
                text = LoginScreenConstants.sign_up_Text,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h4
            )
        }
    }
}