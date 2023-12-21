package com.spaceapp.presentation.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.spaceapp.core.common.helper.Device
import com.spaceapp.core.common.helper.EmailController
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.common.TaskResult
import com.spaceapp.domain.model.auth.Login
import com.spaceapp.domain.usecase.auth.LoginUseCase
import com.spaceapp.presentation.login.state.LoginInputFieldState
import com.spaceapp.presentation.login.state.LoginState
import com.spaceapp.presentation.utils.SignUpResponseMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    application: Application
) : ViewModel() {

    var device: MobileServiceType =
        Device.mobileServiceType(context = application.applicationContext)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkLoginInfo()) {
                if (device == MobileServiceType.HMS) {
                    loginUseCase.hmsAuth(
                        login = Login(
                            userEmail = email,
                            userPassword = password
                        )
                    ).collect { result ->
                        when (result) {
                            is TaskResult.Success -> {
                                _uiState.update {
                                    it.copy(loginState = LoginState.Loading)
                                }
                                result.data
                                    ?.addOnSuccessListener {
                                        _uiState.update {
                                            it.copy(loginState = LoginState.Success)
                                        }
                                    }
                                    ?.addOnFailureListener { exception ->
                                        _uiState.update {
                                            it.copy(
                                                loginState = LoginState.Error(
                                                    errorMessage = exception.message
                                                        ?: SignUpResponseMessages.error
                                                )
                                            )
                                        }
                                    }
                            }

                            is TaskResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        loginState = LoginState.Error(
                                            errorMessage = result.message
                                                ?: SignUpResponseMessages.error
                                        )
                                    )
                                }
                            }
                        }
                    }
                } else {
                    loginUseCase.firebaseAuth(
                        login = Login(
                            userEmail = email,
                            userPassword = password
                        )
                    ).collect { result ->
                        when (result) {
                            is TaskResult.Success -> {
                                _uiState.update {
                                    it.copy(loginState = LoginState.Loading)
                                }
                                result.data
                                    ?.addOnSuccessListener {
                                        if (checkUserEmailIsVerified()) {
                                            _uiState.update {
                                                it.copy(loginState = LoginState.Success)
                                            }
                                        } else {
                                            _uiState.update {
                                                it.copy(loginState = LoginState.Error(errorMessage = SignUpResponseMessages.unverified_email))
                                            }
                                        }
                                    }
                                    ?.addOnFailureListener { exception ->
                                        _uiState.update {
                                            it.copy(
                                                loginState = LoginState.Error(
                                                    errorMessage = exception.message
                                                        ?: SignUpResponseMessages.error
                                                )
                                            )
                                        }
                                    }
                            }

                            is TaskResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        loginState = LoginState.Error(
                                            errorMessage = result.message
                                                ?: SignUpResponseMessages.error
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // created for firebase auth
    private fun checkUserEmailIsVerified(): Boolean {
        return FirebaseAuth.getInstance().currentUser?.isEmailVerified == true
    }

    private fun checkLoginInfo(): Boolean = checkEmail() && checkPassword()

    private fun checkEmail(): Boolean {
        return if (EmailController.emailController(email)) {
            _uiState.update {
                it.copy(inputFieldsState = LoginInputFieldState.Nothing)
            }
            true
        } else {
            _uiState.update {
                it.copy(inputFieldsState =
                LoginInputFieldState.Error(errorMessage = SignUpResponseMessages.valid_email))
            }
            false
        }
    }

    private fun checkPassword(): Boolean {
        return if (password.length < 8) {
            _uiState.update {
                it.copy(inputFieldsState =
                LoginInputFieldState.Error(errorMessage = SignUpResponseMessages.password_length))
            }
            false
        } else {
            _uiState.update {
                it.copy(inputFieldsState = LoginInputFieldState.Nothing)
            }
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
        _uiState.update {
            it.copy(inputFieldsState = LoginInputFieldState.Nothing)
        }
    }

    // created for error card
    fun resetState() {
        _uiState.update {
            it.copy(loginState = LoginState.Nothing)
        }
        email = ""
        password = ""
    }
}

data class UiState(
    val inputFieldsState: LoginInputFieldState = LoginInputFieldState.Nothing,
    val loginState: LoginState = LoginState.Nothing
)