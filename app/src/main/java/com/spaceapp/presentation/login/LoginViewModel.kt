package com.spaceapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.EmailController
import com.spaceapp.core.common.Result
import com.spaceapp.domain.model.hms.Login
import com.spaceapp.domain.usecase.hms_auth.LoginUseCase
import com.spaceapp.presentation.utils.SignUpResponseMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginInputFieldState = MutableStateFlow<LoginInputFieldState>(LoginInputFieldState.Nothing)
    val loginInputFieldState = _loginInputFieldState.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Nothing)
    val loginState = _loginState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun login() = viewModelScope.launch(Dispatchers.IO) {
        if (checkLoginInfo()) {
            loginUseCase(
                login = Login(
                    userEmail = email,
                    password = password
                )
            ).collect() { result ->
                when (result) {
                    is Result.Loading -> {
                        _loginState.value = LoginState.Loading
                    }
                    is Result.Success -> {
                        result.data
                            ?.addOnSuccessListener {
                                _loginState.value = LoginState.Success
                            }
                            ?.addOnFailureListener {
                                _loginState.value = LoginState.Error(errorMessage = it.message ?: SignUpResponseMessages.error)
                            }
                    }
                    is Result.Error -> {
                        _loginState.value = LoginState.Error(errorMessage = result.message ?: SignUpResponseMessages.error)
                    }
                }
            }
        }
    }

    private fun checkLoginInfo(): Boolean = checkEmail() && checkPassword()

    private fun checkEmail(): Boolean {
        return if (EmailController.emailController(email)) {
            _loginInputFieldState.value = LoginInputFieldState.Nothing
            true
        } else {
            _loginInputFieldState.value = LoginInputFieldState.Error(errorMessage = SignUpResponseMessages.valid_email)
            false
        }
    }

    private fun checkPassword(): Boolean {
        return if (password.length < 8) {
            _loginInputFieldState.value = LoginInputFieldState.Error(errorMessage = SignUpResponseMessages.password_length)
            false
        } else {
            _loginInputFieldState.value = LoginInputFieldState.Nothing
            true
        }
    }

    fun updateEmailField(newValue: String) {
        email = newValue
    }

    fun updatePasswordField(newValue: String) {
        password = newValue
    }

    fun resetLoginInputFieldState() {
        _loginInputFieldState.value = LoginInputFieldState.Nothing
    }

    // created for error card
    fun resetState() {
        _loginState.value = LoginState.Nothing
    }
}