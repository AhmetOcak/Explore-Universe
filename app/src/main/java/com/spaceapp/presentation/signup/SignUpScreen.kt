package com.spaceapp.presentation.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.designsystem.component.*
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
                        LoadingSpinner(modifier = modifier.fillMaxSize())
                    }
                    is SignUpState.Success -> {
                        navNextScreen(viewModel = viewModel, navController = navController)
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
                        VerifyEmailSection(
                            modifier = modifier,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
            is VerifyEmailState.Error -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ErrorCard(
                        errorDescription = verifyEmailState.errorMessage.toString(),
                        isButtonAvailable = true,
                        onClick = { viewModel.resetState() }
                    )
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

private fun navNextScreen(viewModel: SignUpViewModel, navController: NavController) {
    if (viewModel.device == MobileServiceType.HMS) {
        navController.navigate(NavScreen.HomeScreen.route) {
            popUpTo(NavScreen.HomeScreen.route)
        }
    }
}