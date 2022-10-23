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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.core.ui.component.*
import com.spaceapp.domain.model.Login
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
            LoadingSpinner(modifier = modifier.fillMaxWidth())
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
                ErrorCard(errorDescription = loginState.errorMessage)
            }
        }
        is LoginState.Nothing -> {
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
                DefaultButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    onClick = {
                        viewModel.login(
                            login = Login(
                                userEmail = viewModel.email,
                                password = viewModel.password
                            )
                        )
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

@Composable
private fun TitleSection(modifier: Modifier) {
    Text(
        modifier = modifier.padding(bottom = 72.dp),
        text = constants.welcome_title,
        style = MaterialTheme.typography.h1
    )
}

@Composable
private fun EmailPasswordSection(
    modifier: Modifier,
    viewModel: LoginViewModel
) {
    DefaultOutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 36.dp, bottom = 16.dp),
        onValueChanged = { viewModel.updateEmailField(newValue = it) },
        labelText = constants.email_field,
        keyboardType = KeyboardType.Email,
        leadingIconId = R.drawable.ic_baseline_email,
        value = viewModel.email
    )
    DefaultOutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        onValueChanged = { viewModel.updatePasswordField(newValue = it) },
        labelText = constants.password_field,
        keyboardType = KeyboardType.Password,
        leadingIconId = R.drawable.ic_baseline_key,
        value = viewModel.password
    )
}

@Composable
private fun SignUpSection(
    modifier: Modifier,
    navController: NavController,
    viewModel: LoginViewModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = constants.no_account_text,
            fontSize = 16.sp
        )
        TextButton(
            onClick = {
                viewModel.resetLoginInputFieldState()
                navController.navigate(NavScreen.SignUpScreen.route)
            }
        ) {
            Text(
                text = constants.sign_up_Text,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h4
            )
        }
    }
}