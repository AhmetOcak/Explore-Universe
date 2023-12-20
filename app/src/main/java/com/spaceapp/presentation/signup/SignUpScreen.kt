package com.spaceapp.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.designsystem.components.DefaultButton
import com.spaceapp.core.designsystem.components.DefaultOutlinedTextField
import com.spaceapp.core.designsystem.components.ErrorCard
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.signup.components.VerifyEmailSection
import com.spaceapp.presentation.signup.state.SignUpInputFieldState
import com.spaceapp.presentation.signup.state.SignUpState
import com.spaceapp.presentation.signup.state.VerifyEmailState
import com.spaceapp.presentation.utils.SignUpScreenConstants

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val verifyEmailState by viewModel.verifyEmailState.collectAsState()
    val inputFieldState by viewModel.inputFieldState.collectAsState()
    val signUpState by viewModel.signUpState.collectAsState()

    SignUpContent(
        modifier = modifier,
        navController = navController,
        verifyEmailState = verifyEmailState,
        viewModel = viewModel,
        signUpInputFieldState = inputFieldState,
        signUpState = signUpState,
        emailValue = viewModel.userEmail,
        onEmailValChange = {
            viewModel.updateUserEmailField(it)
        },
        passwordValue = viewModel.userPassword,
        onPasswordValChange = {
            viewModel.updateUserPasswordField(it)
        },
        confirmPasswordValue = viewModel.userConfirmPassword,
        onConfirmPasswordValChange = {
            viewModel.updateUserConfirmPasswordField(it)
        },
        onSignUpClick = {
            if (viewModel.device == MobileServiceType.HMS) {
                viewModel.verifyEmail()
            } else {
                viewModel.signUp()
            }
        },
        resetState = viewModel::resetState
    )
}

@Composable
private fun SignUpContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: SignUpViewModel,
    verifyEmailState: VerifyEmailState,
    signUpInputFieldState: SignUpInputFieldState,
    signUpState: SignUpState,
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit,
    confirmPasswordValue: String,
    onConfirmPasswordValChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    resetState: () -> Unit
) {
    when (verifyEmailState) {
        is VerifyEmailState.Loading -> {
            LoadingSpinner(modifier = Modifier.fillMaxSize())
        }

        is VerifyEmailState.Success -> {
            when (signUpState) {
                is SignUpState.Loading -> {
                    LoadingSpinner(modifier = Modifier.fillMaxSize())
                }

                is SignUpState.Success -> {
                    if (viewModel.device == MobileServiceType.HMS) {
                        navController.navigate(NavScreen.HomeScreen.route) {
                            popUpTo(NavScreen.HomeScreen.route)
                        }
                    }
                }

                is SignUpState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorCard(
                            errorDescription = signUpState.errorMessage,
                            isButtonAvailable = true,
                            onClick = resetState
                        )
                    }
                }

                is SignUpState.Nothing -> {
                    VerifyEmailSection(
                        modifier = modifier,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }

        is VerifyEmailState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(
                    errorDescription = verifyEmailState.errorMessage.toString(),
                    isButtonAvailable = true,
                    onClick = resetState
                )
            }
        }

        is VerifyEmailState.Nothing -> {
            SignUpSection(
                modifier = modifier,
                signUpInputFieldState = signUpInputFieldState,
                emailValue = emailValue,
                onEmailValChange = onEmailValChange,
                passwordValue = passwordValue,
                onPasswordValChange = onPasswordValChange,
                confirmPasswordValue = confirmPasswordValue,
                onConfirmPasswordValChange = onConfirmPasswordValChange,
                onSignUpClick = onSignUpClick
            )
        }
    }
}

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
        Text(
            modifier = Modifier.padding(bottom = 72.dp),
            text = SignUpScreenConstants.welcome_title,
            style = MaterialTheme.typography.headlineLarge
        )
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