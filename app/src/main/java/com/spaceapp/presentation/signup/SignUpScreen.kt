package com.spaceapp.presentation.signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.core.ui.component.*
import com.spaceapp.presentation.utils.SignUpScreenConstants

private val constants = SignUpScreenConstants

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
        signUpState = signUpState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun SignUpContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: SignUpViewModel,
    verifyEmailState: VerifyEmailState,
    signUpInputFieldState: SignUpInputFieldState,
    signUpState: SignUpState
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        BackgroundImage(
            modifier = modifier.fillMaxSize(),
            imageId = R.drawable.background_image
        )
        when (verifyEmailState) {
            is VerifyEmailState.Loading -> {
                LoadingSpinner(modifier = modifier.fillMaxSize())
            }
            is VerifyEmailState.Success -> {
                when (signUpState) {
                    is SignUpState.Loading -> {
                        LoadingSpinner(modifier = modifier.fillMaxWidth())
                    }
                    is SignUpState.Success -> {
                        navController.navigate(NavScreen.HomeScreen.route) {
                            popUpTo(NavScreen.HomeScreen.route)
                        }
                    }
                    is SignUpState.Error -> {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorCard(
                                errorDescription = signUpState.errorMessage,
                                isButtonAvailable = true,
                                onClick = { viewModel.resetState() }
                            )
                        }
                    }
                    is SignUpState.Nothing -> {
                        VerifyCodeCard(
                            modifier = modifier.fillMaxSize(),
                            userEmail = viewModel.userEmail,
                            value = viewModel.verifyCode,
                            onValueChanged = { viewModel.updateVerifyCodeField(newValue = it) },
                            onClick = {
                                viewModel.signUp()
                            }
                        )
                    }
                }
            }
            is VerifyEmailState.Error -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ErrorCard(
                        errorDescription = verifyEmailState.errorMessage.toString(),
                        isButtonAvailable = true,
                        onClick = { viewModel.resetState() })
                }
            }
            is VerifyEmailState.Nothing -> {
                SignUpSection(
                    modifier = modifier,
                    viewModel = viewModel,
                    signUpInputFieldState = signUpInputFieldState
                )
            }
        }
    }
}

@Composable
private fun SignUpSection(
    modifier: Modifier,
    viewModel: SignUpViewModel,
    signUpInputFieldState: SignUpInputFieldState
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
        Title(modifier = modifier)
        InputTextFieldSection(
            modifier = modifier,
            viewModel = viewModel
        )
        SignUpButton(
            modifier = modifier,
            viewModel = viewModel
        )
        ShowInputFieldErrors(signUpInputFieldState = signUpInputFieldState)
    }
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

@Composable
private fun Title(modifier: Modifier) {
    Text(
        modifier = modifier.padding(bottom = 72.dp),
        text = constants.welcome_title,
        style = MaterialTheme.typography.h1
    )
}

@Composable
private fun InputTextFieldSection(
    modifier: Modifier,
    viewModel: SignUpViewModel
) {
    DefaultOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onValueChanged = { viewModel.updateUserEmailField(newValue = it) },
        labelText = constants.email_field,
        keyboardType = KeyboardType.Email,
        leadingIconId = R.drawable.ic_baseline_email,
        value = viewModel.userEmail
    )
    DefaultOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onValueChanged = { viewModel.updateUserPasswordField(newValue = it) },
        labelText = constants.password_field,
        keyboardType = KeyboardType.Password,
        leadingIconId = R.drawable.ic_baseline_key,
        value = viewModel.userPassword
    )
    DefaultOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onValueChanged = { viewModel.updateUserConfirmPasswordField(newValue = it) },
        labelText = constants.confirm_password_field,
        keyboardType = KeyboardType.Password,
        leadingIconId = R.drawable.ic_baseline_check_box,
        value = viewModel.userConfirmPassword
    )
}

@Composable
private fun SignUpButton(
    modifier: Modifier,
    viewModel: SignUpViewModel
) {
    DefaultButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        onClick = {
            viewModel.verifyEmail()
        },
        contentText = constants.button_text
    )
}