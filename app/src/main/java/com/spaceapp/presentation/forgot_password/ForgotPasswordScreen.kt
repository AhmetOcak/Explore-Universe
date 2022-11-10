package com.spaceapp.presentation.forgot_password

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.core.ui.component.*
import com.spaceapp.core.ui.theme.White
import com.spaceapp.domain.utils.ERROR
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants

private val constants = ForgotPasswordScreenConstants

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
            SendVerifyCodeSection(modifier = modifier, viewModel = viewModel)
            ShowInputFieldErrors(forgotPasswordInputFieldState = forgotPasswordInputFieldState)
        }
        is VerifyForgotPasswordState.Loading -> {
            LoadingSpinner(modifier = modifier.fillMaxWidth())
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
                    errorDescription = verifyForgotPasswordState.errorMessage ?: ERROR.UNKNOWN
                )
            }
        }
    }
}

@Composable
private fun SendVerifyCodeSection(modifier: Modifier, viewModel: ForgotPasswordViewModel) {
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
            painter = painterResource(id = R.drawable.forgot_password_main),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = constants.enter_email,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = White
        )
        DefaultOutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            onValueChanged = { viewModel.updateUserEmailField(newValue = it) },
            labelText = constants.email,
            keyboardType = KeyboardType.Email,
            leadingIconId = R.drawable.ic_baseline_email,
            value = viewModel.userEmail
        )
        DefaultButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            onClick = {
                viewModel.verifyForgotPassword()
            },
            contentText = constants.send_email
        )
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
            ChangePasswordInputSection(modifier = modifier, viewModel = viewModel)
            ShowInputFieldErrors(forgotPasswordInputFieldState = forgotPasswordInputFieldState)
        }
        is ForgotPasswordState.Loading -> {
            LoadingSpinner(modifier = modifier.fillMaxWidth())
        }
        is ForgotPasswordState.Success -> {
            ChangePasswordSuccessSection(modifier = modifier, navController = navController)
        }
        is ForgotPasswordState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(errorDescription = forgotPasswordState.errorMessage ?: ERROR.UNKNOWN)
            }
        }
    }
}

@Composable
private fun ChangePasswordInputSection(modifier: Modifier, viewModel: ForgotPasswordViewModel) {
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
            text = constants.enter_new_password,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = White
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChanged = { viewModel.updateVerifyCodeField(newValue = it) },
            labelText = constants.verification_code,
            keyboardType = KeyboardType.Number,
            leadingIconId = R.drawable.ic_baseline_key,
            value = viewModel.verifyCode
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChanged = { viewModel.updateUserPasswordField(newValue = it) },
            labelText = constants.new_password,
            keyboardType = KeyboardType.Password,
            leadingIconId = R.drawable.ic_baseline_key,
            value = viewModel.userPassword
        )
        DefaultOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onValueChanged = { viewModel.updateUserConfirmPasswordField(newValue = it) },
            labelText = constants.confirm_password,
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
            contentText = constants.save_new_password
        )
    }
}

@Composable
private fun ChangePasswordSuccessSection(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            painter = painterResource(id = R.drawable.forgot_password_success),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            text = constants.success_message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = White
        )
        DefaultButton(
            modifier = modifier
                .fillMaxWidth(),
            onClick = {
                navController.navigate(NavScreen.LoginScreen.route) {
                    popUpTo(0)
                }
            },
            contentText = constants.return_login_page
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

