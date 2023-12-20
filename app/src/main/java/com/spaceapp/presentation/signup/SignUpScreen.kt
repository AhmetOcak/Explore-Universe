package com.spaceapp.presentation.signup

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.designsystem.component.ErrorCard
import com.spaceapp.core.designsystem.component.LoadingSpinner
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.signup.components.SignUpSection
import com.spaceapp.presentation.signup.components.VerifyEmailSection
import com.spaceapp.presentation.signup.state.SignUpInputFieldState
import com.spaceapp.presentation.signup.state.SignUpState
import com.spaceapp.presentation.signup.state.VerifyEmailState

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