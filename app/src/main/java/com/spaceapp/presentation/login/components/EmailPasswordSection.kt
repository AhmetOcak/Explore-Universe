package com.spaceapp.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.DefaultOutlinedTextField
import com.spaceapp.presentation.utils.LoginScreenConstants

@Composable
fun EmailPasswordSection(
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit
) {
    DefaultOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, bottom = 16.dp),
        onValueChanged = onEmailValChange,
        labelText = LoginScreenConstants.email_field,
        keyboardType = KeyboardType.Email,
        leadingIconId = R.drawable.ic_baseline_email,
        value = emailValue
    )
    DefaultOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        onValueChanged = onPasswordValChange,
        labelText = LoginScreenConstants.password_field,
        keyboardType = KeyboardType.Password,
        leadingIconId = R.drawable.ic_baseline_key,
        value = passwordValue
    )
}