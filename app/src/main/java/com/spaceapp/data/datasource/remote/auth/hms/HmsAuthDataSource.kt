package com.spaceapp.data.datasource.remote.auth.hms

import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.auth.entity.ForgotPasswordDto
import com.spaceapp.data.datasource.remote.auth.entity.LoginDto
import com.spaceapp.data.datasource.remote.auth.entity.SignUpDto
import com.spaceapp.data.datasource.remote.auth.entity.VerifyEmailDto

interface HmsAuthDataSource {

    fun verifyUserEmail(verifyEmailDto: VerifyEmailDto): Task<VerifyCodeResult>

    fun signUp(signupDto: SignUpDto): Task<SignInResult>

    fun login(loginDto: LoginDto): Task<SignInResult>

    fun forgotPassword(forgotPasswordDto: ForgotPasswordDto): Task<Void>
}