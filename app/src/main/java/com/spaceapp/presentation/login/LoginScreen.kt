package com.spaceapp.presentation.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.core.designsystem.component.*
import com.spaceapp.presentation.login.components.EmailPasswordSection
import com.spaceapp.presentation.login.components.ForgotPasswordSection
import com.spaceapp.presentation.login.components.SignUpSection
import com.spaceapp.presentation.login.components.TitleSection
import com.spaceapp.presentation.login.state.LoginInputFieldState
import com.spaceapp.presentation.login.state.LoginState
import com.spaceapp.presentation.utils.LoginScreenConstants

private val constants = LoginScreenConstants

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginInputFieldState by viewModel.loginInputFieldState.collectAsState()
    val loginState by viewModel.loginState.collectAsState()

    LoginContent(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel,
        loginState = loginState,
        loginInputFieldState = loginInputFieldState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun LoginContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: LoginViewModel,
    loginState: LoginState,
    loginInputFieldState: LoginInputFieldState
) {
    Scaffold(modifier = modifier) {
        BackgroundImage(
            modifier = modifier,
            imageId = R.drawable.background_image
        )
        LoginSection(
            modifier = modifier,
            navController = navController,
            viewModel = viewModel,
            loginState = loginState,
            loginInputFieldState = loginInputFieldState
        )
    }
}

@Composable
private fun LoginSection(
    modifier: Modifier,
    navController: NavController,
    viewModel: LoginViewModel,
    loginState: LoginState,
    loginInputFieldState: LoginInputFieldState
) {
    when (loginState) {
        is LoginState.Loading -> {
            LoadingSpinner(modifier = modifier.fillMaxSize())
        }
        is LoginState.Success -> {
            navController.navigate(NavScreen.HomeScreen.route) {
                popUpTo(NavScreen.HomeScreen.route) {
                    inclusive = true
                }
            }
        }
        is LoginState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorCard(
                    errorDescription = loginState.errorMessage,
                    isButtonAvailable = true,
                    onClick = { viewModel.resetState() }
                )
            }
        }
        is LoginState.Nothing -> {
            LoginInputSection(
                modifier = modifier,
                viewModel = viewModel,
                navController = navController,
                loginInputFieldState = loginInputFieldState
            )
        }
    }
}

@Composable
private fun LoginInputSection(
    modifier: Modifier,
    viewModel: LoginViewModel,
    navController: NavController,
    loginInputFieldState: LoginInputFieldState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        TitleSection(modifier = modifier)
        EmailPasswordSection(
            modifier = modifier,
            viewModel = viewModel
        )
        ForgotPasswordSection(
            modifier = modifier,
            navController = navController
        )
        DefaultButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = {
                viewModel.login()
            },
            contentText = constants.button_text
        )
        SignUpSection(
            modifier = modifier,
            navController = navController,
            viewModel = viewModel
        )
        ShowInputFieldErrors(
            loginInputFieldState = loginInputFieldState
        )
    }
}

@Composable
private fun ShowInputFieldErrors(loginInputFieldState: LoginInputFieldState) {
    when (loginInputFieldState) {
        is LoginInputFieldState.Error -> {
            Toast.makeText(
                LocalContext.current,
                loginInputFieldState.errorMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
        is LoginInputFieldState.Nothing -> {}
    }
}