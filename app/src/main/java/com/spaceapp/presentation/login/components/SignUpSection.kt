package com.spaceapp.presentation.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.spaceapp.presentation.utils.LoginScreenConstants

@Composable
fun SignUpSection(onSignUpClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = LoginScreenConstants.no_account_text,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xBFFFFFFF))
        )
        TextButton(onClick = onSignUpClick) {
            Text(
                text = LoginScreenConstants.sign_up_Text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}