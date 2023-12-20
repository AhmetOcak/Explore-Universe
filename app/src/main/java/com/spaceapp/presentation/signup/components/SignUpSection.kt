package com.spaceapp.presentation.signup.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.DefaultButton
import com.spaceapp.core.designsystem.component.DefaultOutlinedTextField
import com.spaceapp.presentation.signup.state.SignUpInputFieldState
import com.spaceapp.presentation.utils.SignUpScreenConstants

@Composable
fun SignUpSection(
    modifier: Modifier,
    signUpInputFieldState: SignUpInputFieldState,
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit,
    confirmPasswordValue: String,
    onConfirmPasswordValChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Title(modifier = Modifier.padding(bottom = 72.dp))
        InputTextFields(
            emailValue = emailValue,
            onEmailValChange = onEmailValChange,
            passwordValue = passwordValue,
            onPasswordValChange = onPasswordValChange,
            confirmPasswordValue = confirmPasswordValue,
            onConfirmPasswordValChange = onConfirmPasswordValChange
        )
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = onSignUpClick,
            contentText = SignUpScreenConstants.button_text
        )
        ShowInputFieldErrors(signUpInputFieldState = signUpInputFieldState)
    }
}

@Composable
private fun Title(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = SignUpScreenConstants.welcome_title,
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
private fun InputTextFields(
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit,
    confirmPasswordValue: String,
    onConfirmPasswordValChange: (String) -> Unit,
) {
    DefaultOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onValueChanged = onEmailValChange,
        labelText = SignUpScreenConstants.email_field,
        keyboardType = KeyboardType.Email,
        leadingIconId = R.drawable.ic_baseline_email,
        value = emailValue
    )
    DefaultOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onValueChanged = onPasswordValChange,
        labelText = SignUpScreenConstants.password_field,
        keyboardType = KeyboardType.Password,
        leadingIconId = R.drawable.ic_baseline_key,
        value = passwordValue
    )
    DefaultOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onValueChanged = onConfirmPasswordValChange,
        labelText = SignUpScreenConstants.confirm_password_field,
        keyboardType = KeyboardType.Password,
        leadingIconId = R.drawable.ic_baseline_check_box,
        value = confirmPasswordValue
    )
}

@Composable
private fun ShowInputFieldErrors(signUpInputFieldState: SignUpInputFieldState) {
    when (signUpInputFieldState) {
        is SignUpInputFieldState.Error -> {
            Toast.makeText(
                LocalContext.current,
                signUpInputFieldState.errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

        is SignUpInputFieldState.Nothing -> {}
    }
}

