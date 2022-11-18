package com.spaceapp.data.datasource.remote.auth.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.spaceapp.data.datasource.remote.auth.entity.ForgotPasswordDto
import com.spaceapp.data.datasource.remote.auth.entity.LoginDto
import com.spaceapp.data.datasource.remote.auth.entity.SignUpDto
import javax.inject.Inject

class FirebaseAuthDataSourceImpl @Inject constructor() : FirebaseAuthDataSource {

    override fun sendVerifyEmail(): Task<Void>? {
        return FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
    }

    override fun resetPassword(forgotPasswordDto: ForgotPasswordDto): Task<Void> {
        return FirebaseAuth.getInstance().sendPasswordResetEmail(forgotPasswordDto.email)
    }

    override fun signUp(signUpDto: SignUpDto): Task<AuthResult> {
        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(signUpDto.email, signUpDto.password)
    }

    override fun login(loginDto: LoginDto): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(loginDto.userEmail, loginDto.userPassword)
    }
}