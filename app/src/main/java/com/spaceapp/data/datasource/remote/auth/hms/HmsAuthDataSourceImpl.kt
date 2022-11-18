package com.spaceapp.data.datasource.remote.auth.hms

import com.huawei.agconnect.auth.*
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.auth.entity.*
import java.util.*
import javax.inject.Inject

class HmsAuthDataSourceImpl @Inject constructor() : HmsAuthDataSource {

    override fun verifyUserEmail(verifyEmailDto: VerifyEmailDto): Task<VerifyCodeResult> {
        val settings = VerifyCodeSettings.newBuilder()
            .action(verifyEmailDto.action)
            .sendInterval(30)
            .locale(Locale.ENGLISH)
            .build()

        return AGConnectAuth.getInstance().requestVerifyCode(verifyEmailDto.userEmail, settings)
    }

    override fun signUp(signupDto: SignUpDto): Task<SignInResult> {
        val emailUser = EmailUser.Builder()
            .setEmail(signupDto.email)
            .setVerifyCode(signupDto.verifyCode)
            .setPassword(signupDto.password)
            .build()

        return AGConnectAuth.getInstance().createUser(emailUser)
    }

    override fun login(loginDto: LoginDto): Task<SignInResult> {
        val credential = EmailAuthProvider.credentialWithPassword(loginDto.userEmail, loginDto.userPassword)

        return AGConnectAuth.getInstance().signIn(credential)
    }

    override fun forgotPassword(forgotPasswordDto: ForgotPasswordDto): Task<Void> =
        AGConnectAuth.getInstance().resetPassword(
            forgotPasswordDto.email,
            forgotPasswordDto.newPassword,
            forgotPasswordDto.verifyCode
        )
}