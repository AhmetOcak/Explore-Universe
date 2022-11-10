package com.spaceapp.presentation.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spaceapp.core.common.EmailController
import com.spaceapp.core.common.Result
import com.spaceapp.domain.model.ForgotPassword
import com.spaceapp.domain.model.VerifyForgotPassword
import com.spaceapp.domain.usecase.hms_auth.ForgotPasswordUseCase
import com.spaceapp.domain.usecase.hms_auth.VerifyForgotPasswordUseCase
import com.spaceapp.presentation.utils.ForgotPasswordScreenConstants
import com.spaceapp.presentation.utils.SignUpResponseMessages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private val constants = ForgotPasswordScreenConstants

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val verifyForgotPasswordUseCase: VerifyForgotPasswordUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
) : ViewModel() {

    private val _verifyForgotPasswordState = MutableStateFlow<VerifyForgotPasswordState>(VerifyForgotPasswordState.Nothing)
    val verifyForgotPasswordState = _verifyForgotPasswordState.asStateFlow()

    private val _forgotPasswordState = MutableStateFlow<ForgotPasswordState>(ForgotPasswordState.Nothing)
    val forgotPasswordState = _forgotPasswordState.asStateFlow()

    private val _forgotPasswordInputFieldState = MutableStateFlow<ForgotPasswordInputFieldState>(ForgotPasswordInputFieldState.Nothing)
    val forgotPasswordInputFieldState = _forgotPasswordInputFieldState.asStateFlow()

    var userEmail by mutableStateOf("")
        private set
    var userPassword by mutableStateOf("")
        private set
    var userConfirmPassword by mutableStateOf("")
        private set

    var verifyCode by mutableStateOf("")
        private set

    fun verifyForgotPassword() =
        viewModelScope.launch(Dispatchers.IO) {
            if (checkEmail()) {
                verifyForgotPasswordUseCase(verifyForgotPassword = VerifyForgotPassword(userEmail = userEmail)).collect() { result ->
                    when (result) {
                        is Result.Loading -> {
                            _verifyForgotPasswordState.value = VerifyForgotPasswordState.Loading
                        }
                        is Result.Success -> {
                            result.data
                                ?.addOnSuccessListener {
                                    _verifyForgotPasswordState.value = VerifyForgotPasswordState.Success
                                }
                                ?.addOnFailureListener {
                                    _verifyForgotPasswordState.value = VerifyForgotPasswordState.Error(errorMessage = it.message)
                                }
                        }
                        is Result.Error -> {
                            _verifyForgotPasswordState.value = VerifyForgotPasswordState.Error(errorMessage = result.message)
                        }
                    }
                }
            }
        }

    fun changePassword() = viewModelScope.launch(Dispatchers.IO) {
        forgotPasswordUseCase(
            forgotPassword = ForgotPassword(
                email = userEmail,
                newPassword = userPassword,
                verifyCode = verifyCode
            )
        ).collect() { result ->
            if (confirmPassword() && checkPassword()) {
                when (result) {
                    is Result.Loading -> {
                        _forgotPasswordState.value = ForgotPasswordState.Loading
                    }
                    is Result.Success -> {
                        result.data
                            ?.addOnSuccessListener {
                                _forgotPasswordState.value = ForgotPasswordState.Success
                            }
                            ?.addOnFailureListener {
                                _forgotPasswordState.value = ForgotPasswordState.Error(errorMessage = it.message)
                            }
                    }
                    is Result.Error -> {
                        _forgotPasswordState.value = ForgotPasswordState.Error(errorMessage = result.message)
                    }
                }
            }
        }
    }

    private fun checkEmail(): Boolean {
        return if (EmailController.emailController(userEmail)) {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Nothing
            true
        } else {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Error(errorMessage = constants.fill_all_fields)
            false
        }
    }

    private fun checkPassword(): Boolean {
        return if (userPassword.length < 8) {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Error(errorMessage = constants.password_length)
            false
        } else {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Nothing
            true
        }
    }

    private fun checkVerifyCode(): Boolean {
        return if (verifyCode.isEmpty()) {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Error(errorMessage = constants.fill_all_fields)
            false
        } else {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Nothing
            true
        }
    }

    private fun confirmPassword(): Boolean {
        return if (userPassword != userConfirmPassword) {
            _forgotPasswordInputFieldState.value = ForgotPasswordInputFieldState.Error(errorMessage = SignUpResponseMessages.password_match)
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
}