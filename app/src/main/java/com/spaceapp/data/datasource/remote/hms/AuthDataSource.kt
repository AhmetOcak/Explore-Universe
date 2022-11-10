package com.spaceapp.data.datasource.remote.hms

import com.huawei.agconnect.auth.*
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.hms.entity.*
import java.util.*
import javax.inject.Inject

class AuthDataSource @Inject constructor() {

    fun verifyUserEmail(verifyRegisterLoginDto: VerifyRegisterLoginDto): Task<VerifyCodeResult> {
        val settings = VerifyCodeSettings.newBuilder()
            .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
            .sendInterval(30)
            .locale(Locale.ENGLISH)
            .build()

        return AGConnectAuth.getInstance().requestVerifyCode(verifyRegisterLoginDto.userEmail, settings)
    }

    fun verifyUserEmail(verifyForgotPasswordDto: VerifyForgotPasswordDto): Task<VerifyCodeResult> {
        val settings = VerifyCodeSettings.newBuilder()
            .action(VerifyCodeSettings.ACTION_RESET_PASSWORD)
            .sendInterval(30)
            .locale(Locale.ENGLISH)
            .build()

        return AGConnectAuth.getInstance().requestVerifyCode(verifyForgotPasswordDto.userEmail, settings)
    }

    fun signUp(signupDto: SignUpDto): Task<SignInResult> {
        val emailUser = EmailUser.Builder()
            .setEmail(signupDto.email)
            .setVerifyCode(signupDto.verifyCode)
            .setPassword(signupDto.password)
            .build()

        return AGConnectAuth.getInstance().createUser(emailUser)
    }

    fun login(loginDto: LoginDto): Task<SignInResult> {
        val credential = EmailAuthProvider.credentialWithPassword(loginDto.userEmail, loginDto.password)

        return AGConnectAuth.getInstance().signIn(credential)
    }

    fun forgotPassword(forgotPasswordDto: ForgotPasswordDto): Task<Void> =
        AGConnectAuth.getInstance().resetPassword(
            forgotPasswordDto.email,
            forgotPasswordDto.newPassword,
            forgotPasswordDto.verifyCode
        )
}