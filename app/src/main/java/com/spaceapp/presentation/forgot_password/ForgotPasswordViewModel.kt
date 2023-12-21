package com.spaceapp.presentation.forgot_password

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.agconnect.auth.VerifyCodeSettings
import com.spaceapp.core.common.helper.Device
import com.spaceapp.core.common.helper.EmailController
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.core.common.TaskResult
import com.spaceapp.domain.model.auth.ForgotPassword
import com.spaceapp.domain.model.auth.VerifyEmail
import com.spaceapp.domain.usecase.auth.ForgotPasswordUseCase
import com.spaceapp.domain.usecase.auth.VerifyUserEmailUseCase
import com.spaceapp.presentation.forgot_password.state.ForgotPasswordInputFieldState
import com.spaceapp.presentation.forgot_password.state.ForgotPasswordState
import com.spaceapp.presentation.forgot_password.state.VerifyForgotPasswordState
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants
import com.spaceapp.presentation.utils.SignUpResponseMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private val constants = ForgotPasswordScreenConstants

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val verifyUserEmailUseCase: VerifyUserEmailUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    application: Application
) : ViewModel() {

    var device: MobileServiceType =
        Device.mobileServiceType(context = application.applicationContext)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var userEmail by mutableStateOf("")
        private set
    var userPassword by mutableStateOf("")
        private set
    var userConfirmPassword by mutableStateOf("")
        private set

    var verifyCode by mutableStateOf("")
        private set

    fun verifyForgotPassword() {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkEmail()) {
                if (device == MobileServiceType.HMS) {
                    verifyUserEmailUseCase.hmsAuth(
                        verifyEmail = VerifyEmail(
                            userEmail = userEmail,
                            action = VerifyCodeSettings.ACTION_RESET_PASSWORD
                        )
                    ).collect { result ->
                        _uiState.update {
                            it.copy(verifyForgotPasswordState = VerifyForgotPasswordState.Loading)
                        }
                        when (result) {
                            is TaskResult.Success -> {
                                result.data
                                    ?.addOnSuccessListener {
                                        _uiState.update {
                                            it.copy(verifyForgotPasswordState = VerifyForgotPasswordState.Success)
                                        }
                                    }
                                    ?.addOnFailureListener { exception ->
                                        _uiState.update {
                                            it.copy(
                                                verifyForgotPasswordState = VerifyForgotPasswordState.Error(
                                                    errorMessage = exception.message
                                                )
                                            )
                                        }
                                    }
                            }

                            is TaskResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        verifyForgotPasswordState = VerifyForgotPasswordState.Error(
                                            errorMessage = result.message
                                        )
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // in firebase we don't verify email when we want reset password
                    _uiState.update {
                        it.copy(
                            verifyForgotPasswordState = VerifyForgotPasswordState.Success
                        )
                    }

                    forgotPasswordUseCase.firebaseAuth(
                        forgotPassword = ForgotPassword(
                            email = userEmail,
                            newPassword = "",
                            verifyCode = ""
                        )
                    ).collect { result ->
                        when (result) {
                            is TaskResult.Success -> {
                                _uiState.update {
                                    it.copy(forgotPasswordState = ForgotPasswordState.Loading)
                                }
                                result.data
                                    ?.addOnSuccessListener {
                                        _uiState.update {
                                            it.copy(forgotPasswordState = ForgotPasswordState.Success)
                                        }
                                    }
                                    ?.addOnFailureListener { exception ->
                                        _uiState.update {
                                            it.copy(
                                                forgotPasswordState = ForgotPasswordState.Error(
                                                    errorMessage = exception.message
                                                )
                                            )
                                        }
                                    }
                            }

                            is TaskResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        forgotPasswordState = ForgotPasswordState.Error(
                                            errorMessage = result.message
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

    fun changePassword() {
        viewModelScope.launch(Dispatchers.IO) {
            if (device == MobileServiceType.HMS) {
                forgotPasswordUseCase.hmsAuth(
                    forgotPassword = ForgotPassword(
                        email = userEmail,
                        newPassword = userPassword,
                        verifyCode = verifyCode
                    )
                ).collect { result ->
                    if (confirmPassword() && checkPassword()) {
                        when (result) {
                            is TaskResult.Success -> {
                                _uiState.update {
                                    it.copy(forgotPasswordState = ForgotPasswordState.Loading)
                                }
                                result.data
                                    ?.addOnSuccessListener {
                                        _uiState.update {
                                            it.copy(forgotPasswordState = ForgotPasswordState.Success)
                                        }
                                    }
                                    ?.addOnFailureListener { exception ->
                                        _uiState.update {
                                            it.copy(
                                                forgotPasswordState = ForgotPasswordState.Error(
                                                    errorMessage = exception.message
                                                )
                                            )
                                        }
                                    }
                            }

                            is TaskResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        forgotPasswordState = ForgotPasswordState.Error(
                                            errorMessage = result.message
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

    private fun checkEmail(): Boolean {
        return if (EmailController.emailController(userEmail)) {
            _uiState.update {
                it.copy(inputFieldState = ForgotPasswordInputFieldState.Nothing)
            }
            true
        } else {
            _uiState.update {
                it.copy(
                    inputFieldState =
                    ForgotPasswordInputFieldState.Error(errorMessage = constants.fill_all_fields)
                )
            }
            false
        }
    }

    private fun checkPassword(): Boolean {
        return if (userPassword.length < 8) {
            _uiState.update {
                it.copy(
                    inputFieldState =
                    ForgotPasswordInputFieldState.Error(errorMessage = constants.password_length)
                )
            }
            false
        } else {
            _uiState.update {
                it.copy(inputFieldState = ForgotPasswordInputFieldState.Nothing)
            }
            true
        }
    }

    private fun confirmPassword(): Boolean {
        return if (userPassword != userConfirmPassword) {
            _uiState.update {
                it.copy(inputFieldState =
                ForgotPasswordInputFieldState.Error(errorMessage = SignUpResponseMessages.password_match))
            }
            false
        } else {
            true
        }
    }

    fun updateUserEmailField(newValue: String) {
        userEmail = newValue
    }

    fun updateUserPasswordField(newValue: String) {
        userPassword = newValue
    }

    fun updateUserConfirmPasswordField(newValue: String) {
        userConfirmPassword = newValue
    }

    fun updateVerifyCodeField(newValue: String) {
        verifyCode = newValue
    }

    // created for error card
    fun resetState() {
        _uiState.update {
            it.copy(
                forgotPasswordState = ForgotPasswordState.Nothing,
                verifyForgotPasswordState = VerifyForgotPasswordState.Nothing
            )
        }
    }
}

data class UiState(
    val inputFieldState: ForgotPasswordInputFieldState = ForgotPasswordInputFieldState.Nothing,
    val forgotPasswordState: ForgotPasswordState = ForgotPasswordState.Nothing,
    val verifyForgotPasswordState: VerifyForgotPasswordState = VerifyForgotPasswordState.Nothing
)