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
import com.spaceapp.core.designsystem.components.DefaultButton
import com.spaceapp.core.designsystem.components.DefaultOutlinedTextField
import com.spaceapp.core.designsystem.theme.White
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants

// For HMS
@Composable
fun SendVerifyCode(
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    onSendCodeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            painter = painterResource(id = R.drawable.forgot_password_main),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = ForgotPasswordScreenConstants.enter_email,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = White
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            onValueChanged = onEmailValChange,
            labelText = ForgotPasswordScreenConstants.email,
            keyboardType = KeyboardType.Email,
            leadingIconId = R.drawable.ic_baseline_email,
            value = emailValue
        )
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            onClick = onSendCodeClick,
            contentText = ForgotPasswordScreenConstants.send_email_hms
        )
    }
}