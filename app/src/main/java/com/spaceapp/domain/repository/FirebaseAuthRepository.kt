package com.spaceapp.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.spaceapp.domain.model.auth.*

interface FirebaseAuthRepository {

    fun verifyEmail(): Task<Void>?

    fun forgotPassword(forgotPassword: ForgotPassword): Task<Void>

    fun signUp(signUp: SignUp): Task<AuthResult>

    fun login(login: Login): Task<AuthResult>
}