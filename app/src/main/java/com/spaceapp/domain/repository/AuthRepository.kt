package com.spaceapp.domain.repository

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.domain.model.Login
import com.spaceapp.domain.model.User

interface AuthRepository {

    fun signUp(user: User, verifyCode: String) : Task<SignInResult>

    fun login(login: Login): Task<SignInResult>

    fun verifyEmail(email: String) : Task<VerifyCodeResult>
}