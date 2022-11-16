package com.spaceapp.domain.repository

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.domain.model.auth.*

interface HmsAuthRepository {

    fun signUp(signUp: SignUp) : Task<SignInResult>

    fun login(login: Login): Task<SignInResult>

    fun verifyUserEmail(verifyEmail: VerifyEmail) : Task<VerifyCodeResult>

    fun forgotPassword(forgotPassword: ForgotPassword): Task<Void>
}