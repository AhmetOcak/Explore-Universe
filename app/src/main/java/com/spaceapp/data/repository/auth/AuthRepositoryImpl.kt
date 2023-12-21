package com.spaceapp.data.repository.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.spaceapp.data.datasource.remote.auth.firebase.FirebaseAuthDataSource
import com.spaceapp.data.datasource.remote.auth.hms.HmsAuthDataSource
import com.spaceapp.data.mappers.toForgotPasswordDto
import com.spaceapp.data.mappers.toLoginDto
import com.spaceapp.data.mappers.toSignUpDto
import com.spaceapp.data.mappers.toVerifyEmailDto
import com.spaceapp.domain.model.auth.ForgotPassword
import com.spaceapp.domain.model.auth.Login
import com.spaceapp.domain.model.auth.SignUp
import com.spaceapp.domain.model.auth.VerifyEmail
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val gmsAuthDataSource: FirebaseAuthDataSource,
    private val hmsAuthDataSource: HmsAuthDataSource
) : AuthRepository {
    override fun verifyEmailGMS(): Task<Void>? =
        gmsAuthDataSource.sendVerifyEmail()

    override fun forgotPasswordGMS(forgotPassword: ForgotPassword): Task<Void> =
        gmsAuthDataSource.resetPassword(forgotPasswordDto = forgotPassword.toForgotPasswordDto())

    override fun signUpGMS(signUp: SignUp): Task<AuthResult> =
        gmsAuthDataSource.signUp(signUpDto = signUp.toSignUpDto())

    override fun loginGMS(login: Login): Task<AuthResult> =
        gmsAuthDataSource.login(loginDto = login.toLoginDto())

    override fun signUpHMS(signUp: SignUp): com.huawei.hmf.tasks.Task<SignInResult> =
        hmsAuthDataSource.signUp(signupDto = signUp.toSignUpDto())

    override fun loginHMS(login: Login): com.huawei.hmf.tasks.Task<SignInResult> =
        hmsAuthDataSource.login(loginDto = login.toLoginDto())

    override fun verifyUserEmailHMS(verifyEmail: VerifyEmail): com.huawei.hmf.tasks.Task<VerifyCodeResult> =
        hmsAuthDataSource.verifyUserEmail(verifyEmailDto = verifyEmail.toVerifyEmailDto())

    override fun forgotPasswordHMS(forgotPassword: ForgotPassword): com.huawei.hmf.tasks.Task<Void> =
        hmsAuthDataSource.forgotPassword(forgotPasswordDto = forgotPassword.toForgotPasswordDto())
}