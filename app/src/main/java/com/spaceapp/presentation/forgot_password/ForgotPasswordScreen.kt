package com.spaceapp.presentation.forgot_password

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.designsystem.component.*
import com.spaceapp.domain.utils.ERROR
import com.spaceapp.presentation.forgot_password.components.*
import com.spaceapp.presentation.forgot_password.state.*

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val verifyForgotPasswordState by viewModel.verifyForgotPasswordState.collectAsState()
    val forgotPasswordState by viewModel.forgotPasswordState.collectAsState()
    val forgotPasswordInputFieldState by viewModel.forgotPasswordInputFieldState.collectAsState()

    ForgotPasswordScreenContent(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel,
        verifyForgotPasswordState = verifyForgotPasswordState,
        forgotPasswordState = forgotPasswordState,
        forgotPasswordInputFieldState = forgotPasswordInputFieldState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ForgotPasswordScreenContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: ForgotPasswordViewModel,
    verifyForgotPasswordState: VerifyForgotPasswordState,
    forgotPasswordState: ForgotPasswordState,
    forgotPasswordInputFieldState: ForgotPasswordInputFieldState
) {
    Scaffold(modifier = modifier) {
        BackgroundImage(
            modifier = modifier,
            imageId = R.drawable.background_image
        )
        ForgotPasswordSection(
            modifier = modifier,
            navController = navController,
            viewModel = viewModel,
            verifyForgotPasswordState = verifyForgotPasswordState,
            forgotPasswordState = forgotPasswordState,
            forgotPasswordInputFieldState = forgotPasswordInputFieldState
        )
    }
}

@Composable
private fun ForgotPasswordSection(
    modifier: Modifier,
    navController: NavController,
    viewModel: ForgotPasswordViewModel,
    verifyForgotPasswordState: VerifyForgotPasswordState,
    forgotPasswordState: ForgotPasswordState,
    forgotPasswordInputFieldState: ForgotPasswordInputFieldState
) {
    when (verifyForgotPasswordState) {
        is VerifyForgotPasswordState.Nothing -> {
            SendVerifyCode(modifier = modifier, viewModel = viewModel)
            ShowInputFieldErrors(forgotPasswordInputFieldState = forgotPasswordInputFieldState)
        }
        is VerifyForgotPasswordState.Loading -> {
            LoadingSpinner(modifier = modifier.fillMaxSize())
        }
        is VerifyForgotPasswordState.Success -> {
            ChangePasswordSection(
                modifier = modifier,
                forgotPasswordState = forgotPasswordState,
                viewModel = viewModel,
                navController = navController,
                forgotPasswordInputFieldState = forgotPasswordInputFieldState
            )
        }
        is VerifyForgotPasswordState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(
                    errorDescription = verifyForgotPasswordState.errorMessage ?: ERROR.UNKNOWN,
                    isButtonAvailable = true,
                    onClick = { viewModel.resetState() }
                )
            }
        }
    }
}

@Composable
private fun ChangePasswordSection(
    modifier: Modifier,
    forgotPasswordState: ForgotPasswordState,
    viewModel: ForgotPasswordViewModel,
    navController: NavController,
    forgotPasswordInputFieldState: ForgotPasswordInputFieldState
) {
    when (forgotPasswordState) {
        is ForgotPasswordState.Nothing -> {
            if (viewModel.device == MobileServiceType.HMS) {
                PasswordChangeInputSection(modifier = modifier, viewModel = viewModel)
                ShowInputFieldErrors(forgotPasswordInputFieldState = forgotPasswordInputFieldState)
            } else {
                LoadingSpinner(modifier = modifier.fillMaxSize())
            }
        }
        is ForgotPasswordState.Loading -> {
            LoadingSpinner(modifier = modifier.fillMaxSize())
        }
        is ForgotPasswordState.Success -> {
            if (viewModel.device == MobileServiceType.HMS) {
                PasswordChangeSuccessView(modifier = modifier, navController = navController)
            } else {
                SendPasswordResetMail(
                    modifier = modifier,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
        is ForgotPasswordState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(
                    errorDescription = forgotPasswordState.errorMessage ?: ERROR.UNKNOWN,
                    isButtonAvailable = true,
                    onClick = { viewModel.resetState() }
                )
            }
        }
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

