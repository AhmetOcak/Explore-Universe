package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.remote.auth.entity.*
import com.spaceapp.domain.model.auth.ForgotPassword
import com.spaceapp.domain.model.auth.Login
import com.spaceapp.domain.model.auth.SignUp
import com.spaceapp.domain.model.auth.VerifyEmail

fun ForgotPassword.toForgotPasswordDto() : ForgotPasswordDto {
    return ForgotPasswordDto(
        email = email,
        newPassword = newPassword,
        verifyCode = verifyCode
    )
}

fun SignUp.toSignUpDto() : SignUpDto {
    return SignUpDto(
        email = email,
        password = password,
        verifyCode = verifyCode
    )
}

fun Login.toLoginDto() : LoginDto {
    return LoginDto(
        userEmail = userEmail,
        userPassword = userPassword
    )
}

fun VerifyEmail.toVerifyEmailDto() : VerifyEmailDto {
    return VerifyEmailDto(
        userEmail = userEmail,
        action = action
    )
}