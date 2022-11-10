package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.remote.hms.entity.ForgotPasswordDto
import com.spaceapp.data.datasource.remote.hms.entity.SignUpDto
import com.spaceapp.data.datasource.remote.hms.entity.VerifyForgotPasswordDto
import com.spaceapp.data.datasource.remote.hms.entity.VerifyRegisterLoginDto
import com.spaceapp.domain.model.ForgotPassword
import com.spaceapp.domain.model.SignUp
import com.spaceapp.domain.model.VerifyForgotPassword
import com.spaceapp.domain.model.VerifyRegisterLogin

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
