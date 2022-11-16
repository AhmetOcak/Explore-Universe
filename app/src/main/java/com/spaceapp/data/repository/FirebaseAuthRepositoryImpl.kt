package com.spaceapp.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.spaceapp.data.datasource.remote.auth.firebase.FirebaseAuthDataSource
import com.spaceapp.data.mappers.*
import com.spaceapp.domain.model.auth.*
import com.spaceapp.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(private val authDataSource: FirebaseAuthDataSource) : FirebaseAuthRepository {

    override fun verifyEmail(): Task<Void>? =
        authDataSource.sendVerifyEmail()

    override fun forgotPassword(forgotPassword: ForgotPassword): Task<Void> =
        authDataSource.resetPassword(forgotPasswordDto = forgotPassword.toForgotPasswordDto())

    override fun signUp(signUp: SignUp): Task<AuthResult> =
        authDataSource.signUp(signUpDto = signUp.toSignUpDto())

    override fun login(login: Login): Task<AuthResult> =
        authDataSource.login(loginDto = login.toLoginDto())

}