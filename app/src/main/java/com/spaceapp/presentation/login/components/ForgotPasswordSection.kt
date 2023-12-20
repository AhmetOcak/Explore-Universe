package com.spaceapp.presentation.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spaceapp.presentation.utils.LoginScreenConstants

@Composable
fun ForgotPasswordSection(onNavigateForgotPasswordScreen: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        TextButton(
            onClick = onNavigateForgotPasswordScreen
        ) {
            Text(
                text = LoginScreenConstants.forgot_password,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}