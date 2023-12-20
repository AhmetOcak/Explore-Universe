package com.spaceapp.presentation.forgot_password.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.DefaultButton
import com.spaceapp.core.designsystem.component.DefaultOutlinedTextField
import com.spaceapp.core.designsystem.theme.White
import com.spaceapp.presentation.forgot_password.ForgotPasswordViewModel
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants

@Composable
fun PasswordChangeInputSection(modifier: Modifier, viewModel: ForgotPasswordViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            painter = painterResource(id = R.drawable.forgot_password_email),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 48.dp),
            text = ForgotPasswordScreenConstants.enter_new_password,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = White
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChanged = { viewModel.updateVerifyCodeField(newValue = it) },
            labelText = ForgotPasswordScreenConstants.verification_code,
            keyboardType = KeyboardType.Number,
            leadingIconId = R.drawable.ic_baseline_key,
            value = viewModel.verifyCode
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChanged = { viewModel.updateUserPasswordField(newValue = it) },
            labelText = ForgotPasswordScreenConstants.new_password,
            keyboardType = KeyboardType.Password,
            leadingIconId = R.drawable.ic_baseline_key,
            value = viewModel.userPassword
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChanged = { viewModel.updateUserConfirmPasswordField(newValue = it) },
            labelText = ForgotPasswordScreenConstants.confirm_password,
            keyboardType = KeyboardType.Password,
            leadingIconId = R.drawable.ic_baseline_key,
            value = viewModel.userConfirmPassword
        )
        DefaultButton(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                viewModel.changePassword()
            },
            contentText = ForgotPasswordScreenConstants.save_new_password
        )
    }
}