package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.remote.hms.entity.*
import com.spaceapp.domain.model.hms.*

fun VerifyRegisterLogin.toVerifyRegisterLoginDto() : VerifyRegisterLoginDto {
    return VerifyRegisterLoginDto(
        userEmail = userEmail
    )
}

fun VerifyForgotPassword.toVerifyForgotPasswordDto() : VerifyForgotPasswordDto {
    return VerifyForgotPasswordDto(
        userEmail = userEmail
    )
}

fun SignUp.toSignUpDto() : SignUpDto {
    return SignUpDto(
        email = email,
        password = password,
        verifyCode = verifyCode
    )
}

fun ForgotPassword.toForgotPasswordDto() : ForgotPasswordDto {
    return ForgotPasswordDto(
        email = email,
        newPassword = newPassword,
        verifyCode = verifyCode
    )
}
