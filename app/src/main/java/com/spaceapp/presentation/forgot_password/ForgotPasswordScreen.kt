package com.spaceapp.presentation.forgot_password

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.designsystem.components.DefaultButton
import com.spaceapp.core.designsystem.components.ErrorCard
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.core.designsystem.theme.White
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.domain.utils.ERROR
import com.spaceapp.presentation.forgot_password.components.*
import com.spaceapp.presentation.forgot_password.state.*
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val verifyForgotPasswordState by viewModel.verifyForgotPasswordState.collectAsState()
    val forgotPasswordState by viewModel.forgotPasswordState.collectAsState()
    val forgotPasswordInputFieldState by viewModel.forgotPasswordInputFieldState.collectAsState()

    ForgotPasswordScreenContent(
        verifyForgotPasswordState = verifyForgotPasswordState,
        forgotPasswordState = forgotPasswordState,
        forgotPasswordInputFieldState = forgotPasswordInputFieldState,
        onVerifyCodeValChange = {
            viewModel.updateVerifyCodeField(it)
        },
        onPasswordValChange = {
            viewModel.updateUserPasswordField(it)
        },
        onConfirmPasswordValChange = {
            viewModel.updateUserConfirmPasswordField(it)
        },
        onSaveNewPasswordClick = viewModel::changePassword,
        verifyValue = viewModel.verifyCode,
        passwordValue = viewModel.userPassword,
        confirmPasswordValue = viewModel.userConfirmPassword,
        onNavigateLoginScreen = {
            navController.navigate(NavScreen.LoginScreen.route) {
                popUpTo(0)
            }
        },
        deviceType = viewModel.device,
        emailValue = viewModel.userEmail,
        onEmailValChange = {
            viewModel.updateUserEmailField(it)
        },
        onSendCodeClick = viewModel::verifyForgotPassword,
        resetState = viewModel::resetState
    )
}

@Composable
private fun ForgotPasswordScreenContent(
    verifyForgotPasswordState: VerifyForgotPasswordState,
    forgotPasswordState: ForgotPasswordState,
    forgotPasswordInputFieldState: ForgotPasswordInputFieldState,
    onVerifyCodeValChange: (String) -> Unit,
    onPasswordValChange: (String) -> Unit,
    onConfirmPasswordValChange: (String) -> Unit,
    onSaveNewPasswordClick: () -> Unit,
    verifyValue: String,
    passwordValue: String,
    confirmPasswordValue: String,
    onNavigateLoginScreen: () -> Unit,
    deviceType: MobileServiceType,
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    onSendCodeClick: () -> Unit,
    resetState: () -> Unit
) {
    when (verifyForgotPasswordState) {
        is VerifyForgotPasswordState.Nothing -> {
            SendVerifyCode(
                emailValue = emailValue,
                onEmailValChange = onEmailValChange,
                onSendCodeClick = onSendCodeClick
            )
            ShowInputFieldErrors(forgotPasswordInputFieldState = forgotPasswordInputFieldState)
        }

        is VerifyForgotPasswordState.Loading -> {
            LoadingSpinner(modifier = Modifier.fillMaxSize())
        }

        is VerifyForgotPasswordState.Success -> {
            ChangePasswordSection(
                forgotPasswordState = forgotPasswordState,
                forgotPasswordInputFieldState = forgotPasswordInputFieldState,
                onVerifyCodeValChange = onVerifyCodeValChange,
                onPasswordValChange = onPasswordValChange,
                onConfirmPasswordValChange = onConfirmPasswordValChange,
                onSaveNewPasswordClick = onSaveNewPasswordClick,
                verifyValue = verifyValue,
                passwordValue = passwordValue,
                confirmPasswordValue = confirmPasswordValue,
                onNavigateLoginScreen = onNavigateLoginScreen,
                deviceType = deviceType,
                resetState = resetState
            )
        }

        is VerifyForgotPasswordState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(
                    errorDescription = verifyForgotPasswordState.errorMessage ?: ERROR.UNKNOWN,
                    isButtonAvailable = true,
                    onClick = resetState
                )
            }
        }
    }
}

@Composable
private fun ChangePasswordSection(
    forgotPasswordState: ForgotPasswordState,
    forgotPasswordInputFieldState: ForgotPasswordInputFieldState,
    onVerifyCodeValChange: (String) -> Unit,
    onPasswordValChange: (String) -> Unit,
    onConfirmPasswordValChange: (String) -> Unit,
    onSaveNewPasswordClick: () -> Unit,
    verifyValue: String,
    passwordValue: String,
    confirmPasswordValue: String,
    onNavigateLoginScreen: () -> Unit,
    deviceType: MobileServiceType,
    resetState: () -> Unit
) {
    when (forgotPasswordState) {
        is ForgotPasswordState.Nothing -> {
            if (deviceType == MobileServiceType.HMS) {
                InputSection(
                    onVerifyCodeValChange = onVerifyCodeValChange,
                    onPasswordValChange = onPasswordValChange,
                    onConfirmPasswordValChange = onConfirmPasswordValChange,
                    onSaveNewPasswordClick = onSaveNewPasswordClick,
                    verifyValue = verifyValue,
                    passwordValue = passwordValue,
                    confirmPasswordValue = confirmPasswordValue
                )
                ShowInputFieldErrors(forgotPasswordInputFieldState = forgotPasswordInputFieldState)
            } else {
                LoadingSpinner(modifier = Modifier.fillMaxSize())
            }
        }

        is ForgotPasswordState.Loading -> {
            LoadingSpinner(modifier = Modifier.fillMaxSize())
        }

        is ForgotPasswordState.Success -> {
            if (deviceType == MobileServiceType.HMS) {
                SuccessView(onNavigateLoginScreen = onNavigateLoginScreen)
            } else {
                SendPasswordResetMail(
                    onNavigateLoginScreen = onNavigateLoginScreen,
                    deviceType = deviceType
                )
            }
        }

        is ForgotPasswordState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(
                    errorDescription = forgotPasswordState.errorMessage ?: ERROR.UNKNOWN,
                    isButtonAvailable = true,
                    onClick = resetState
                )
            }
        }
    }
}

@Composable
private fun SuccessView(onNavigateLoginScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            painter = painterResource(id = R.drawable.forgot_password_success),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = ForgotPasswordScreenConstants.success_message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = White
        )
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNavigateLoginScreen,
            contentText = ForgotPasswordScreenConstants.return_login_page
        )
    }
}

@Composable
private fun ShowInputFieldErrors(forgotPasswordInputFieldState: ForgotPasswordInputFieldState) {
    when (forgotPasswordInputFieldState) {
        is ForgotPasswordInputFieldState.Error -> {
            Toast.makeText(
                LocalContext.current,
                forgotPasswordInputFieldState.errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

        is ForgotPasswordInputFieldState.Nothing -> {}
    }
}