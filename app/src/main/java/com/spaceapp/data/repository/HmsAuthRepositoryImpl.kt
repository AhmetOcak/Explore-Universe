package com.spaceapp.data.repository

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.hms.HmsAuthDataSource
import com.spaceapp.data.mappers.*
import com.spaceapp.domain.model.hms.*
import com.spaceapp.domain.repository.HmsAuthRepository
import javax.inject.Inject

class HmsAuthRepositoryImpl @Inject constructor(private val authDataSource: HmsAuthDataSource) : HmsAuthRepository {

    override fun verifyEmail(verifyRegisterLogin: VerifyRegisterLogin): Task<VerifyCodeResult> =
        authDataSource.verifyUserEmail(verifyRegisterLoginDto = verifyRegisterLogin.toVerifyRegisterLoginDto())

    override fun verifyEmail(verifyForgotPassword: VerifyForgotPassword): Task<VerifyCodeResult> =
        authDataSource.verifyUserEmail(verifyForgotPasswordDto = verifyForgotPassword.toVerifyForgotPasswordDto())

    override fun signUp(signUp: SignUp): Task<SignInResult> =
        authDataSource.signUp(signupDto = signUp.toSignUpDto())

    override fun login(login: Login): Task<SignInResult> =
        authDataSource.login(loginDto = login.toLoginDto())

    override fun forgotPassword(forgotPassword: ForgotPassword): Task<Void> =
        authDataSource.forgotPassword(forgotPasswordDto = forgotPassword.toForgotPasswordDto())
}