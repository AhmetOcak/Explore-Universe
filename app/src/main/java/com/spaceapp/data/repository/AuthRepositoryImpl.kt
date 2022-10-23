package com.spaceapp.data.repository

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.hms.AuthDataSource
import com.spaceapp.data.mappers.toLoginDto
import com.spaceapp.data.mappers.toUserDto
import com.spaceapp.domain.model.Login
import com.spaceapp.domain.model.User
import com.spaceapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authDataSource: AuthDataSource) : AuthRepository {

    override fun signUp(user: User, verifyCode: String): Task<SignInResult> =
        authDataSource.signUp(userDto = user.toUserDto(), verifyCode = verifyCode)

    override fun login(login: Login): Task<SignInResult> =
        authDataSource.login(loginDto = login.toLoginDto())

    override fun verifyEmail(email: String): Task<VerifyCodeResult> =
        authDataSource.verifyUserEmail(email = email)
}