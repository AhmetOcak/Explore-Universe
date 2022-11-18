package com.spaceapp.data.repository

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.auth.hms.HmsAuthDataSourceImpl
import com.spaceapp.data.mappers.*
import com.spaceapp.domain.model.auth.*
import com.spaceapp.domain.repository.HmsAuthRepository
import javax.inject.Inject

class HmsAuthRepositoryImpl @Inject constructor(private val authDataSource: HmsAuthDataSourceImpl) : HmsAuthRepository {

    override fun verifyUserEmail(verifyEmail: VerifyEmail): Task<VerifyCodeResult> =
        authDataSource.verifyUserEmail(verifyEmailDto = verifyEmail.toVerifyEmailDto())

    override fun signUp(signUp: SignUp): Task<SignInResult> =
        authDataSource.signUp(signupDto = signUp.toSignUpDto())

    override fun login(login: Login): Task<SignInResult> =
        authDataSource.login(loginDto = login.toLoginDto())

    override fun forgotPassword(forgotPassword: ForgotPassword): Task<Void> =
        authDataSource.forgotPassword(forgotPasswordDto = forgotPassword.toForgotPasswordDto())
}