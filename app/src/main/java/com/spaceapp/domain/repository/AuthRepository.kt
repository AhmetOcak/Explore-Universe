package com.spaceapp.domain.repository

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.domain.model.*

interface AuthRepository {

    fun signUp(signUp: SignUp) : Task<SignInResult>

    fun login(login: Login): Task<SignInResult>

    fun verifyEmail(verifyRegisterLogin: VerifyRegisterLogin) : Task<VerifyCodeResult>

    fun verifyEmail(verifyForgotPassword: VerifyForgotPassword) : Task<VerifyCodeResult>

    fun forgotPassword(forgotPassword: ForgotPassword): Task<Void>
}