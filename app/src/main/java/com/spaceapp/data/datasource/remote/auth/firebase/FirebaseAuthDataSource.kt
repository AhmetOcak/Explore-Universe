package com.spaceapp.data.datasource.remote.auth.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.spaceapp.data.datasource.remote.auth.entity.ForgotPasswordDto
import com.spaceapp.data.datasource.remote.auth.entity.LoginDto
import com.spaceapp.data.datasource.remote.auth.entity.SignUpDto

interface FirebaseAuthDataSource {

    fun sendVerifyEmail(): Task<Void>?

    fun resetPassword(forgotPasswordDto: ForgotPasswordDto): Task<Void>

    fun signUp(signUpDto: SignUpDto): Task<AuthResult>

    fun login(loginDto: LoginDto): Task<AuthResult>
}