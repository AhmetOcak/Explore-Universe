package com.spaceapp.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.core.designsystem.components.DefaultButton
import com.spaceapp.core.designsystem.components.ErrorCard
import com.spaceapp.core.designsystem.components.LoadingSpinner
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.presentation.login.components.EmailPasswordSection
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
    val uiState by viewModel.uiState.collectAsState()

    LoginContent(
        modifier = modifier,
        navController = navController,
        loginState = uiState.loginState,
        loginInputFieldState = uiState.inputFieldsState,
        emailValue = viewModel.email,
        onEmailValChange = {
            viewModel.updateEmailField(it)
        },
        passwordValue = viewModel.password,
        onPasswordValChange = {
            viewModel.updatePasswordField(it)
        },
        onNavigateForgotPasswordScreen = {
            navController.navigate(NavScreen.ForgotPasswordScreen.route)
        },
        onSignUpClick = {
            viewModel.resetLoginInputFieldState()
            navController.navigate(NavScreen.SignUpScreen.route)
        },
        onLoginClick = viewModel::login,
        resetState = viewModel::resetState
    )
}

@Composable
private fun LoginContent(
    modifier: Modifier,
    navController: NavController,
    loginState: LoginState,
    loginInputFieldState: LoginInputFieldState,
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit,
    onNavigateForgotPasswordScreen: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    resetState: () -> Unit
) {
    LoginSection(
        modifier = modifier,
        navController = navController,
        loginState = loginState,
        loginInputFieldState = loginInputFieldState,
        emailValue = emailValue,
        onEmailValChange = onEmailValChange,
        passwordValue = passwordValue,
        onPasswordValChange = onPasswordValChange,
        onNavigateForgotPasswordScreen = onNavigateForgotPasswordScreen,
        onSignUpClick = onSignUpClick,
        onLoginClick = onLoginClick,
        resetState = resetState
    )
}

@Composable
private fun LoginSection(
    modifier: Modifier,
    navController: NavController,
    loginState: LoginState,
    loginInputFieldState: LoginInputFieldState,
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit,
    onNavigateForgotPasswordScreen: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    resetState: () -> Unit
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
                    onClick = resetState
                )
            }
        }

        is LoginState.Nothing -> {
            LoginInputSection(
                modifier = modifier,
                loginInputFieldState = loginInputFieldState,
                emailValue = emailValue,
                onEmailValChange = onEmailValChange,
                passwordValue = passwordValue,
                onPasswordValChange = onPasswordValChange,
                onNavigateForgotPasswordScreen = onNavigateForgotPasswordScreen,
                onSignUpClick = onSignUpClick,
                onLoginClick = onLoginClick
            )
        }
    }
}

@Composable
private fun LoginInputSection(
    modifier: Modifier,
    loginInputFieldState: LoginInputFieldState,
    emailValue: String,
    onEmailValChange: (String) -> Unit,
    passwordValue: String,
    onPasswordValChange: (String) -> Unit,
    onNavigateForgotPasswordScreen: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
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
        Text(
            modifier = Modifier.padding(bottom = 72.dp),
            text = LoginScreenConstants.welcome_title,
            style = MaterialTheme.typography.headlineLarge
        )
        EmailPasswordSection(
            emailValue = emailValue,
            onEmailValChange = onEmailValChange,
            passwordValue = passwordValue,
            onPasswordValChange = onPasswordValChange
        )
        ForgotPasswordSection(onNavigateForgotPasswordScreen = onNavigateForgotPasswordScreen)
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = onLoginClick,
            contentText = constants.button_text
        )
        SignUpSection(onSignUpClick = onSignUpClick)
        ShowInputFieldErrors(loginInputFieldState = loginInputFieldState)
    }
}

@Composable
fun SignUpSection(onSignUpClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = LoginScreenConstants.no_account_text,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xBFFFFFFF))
        )
        TextButton(onClick = onSignUpClick) {
            Text(
                text = LoginScreenConstants.sign_up_Text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}

@Composable
fun ForgotPasswordSection(onNavigateForgotPasswordScreen: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        TextButton(
            onClick = onNavigateForgotPasswordScreen
        ) {
            Text(
                text = LoginScreenConstants.forgot_password,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayMedium
            )
        }
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