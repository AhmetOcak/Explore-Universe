package com.spaceapp.data.datasource.remote.hms

import com.huawei.agconnect.auth.*
import com.huawei.hmf.tasks.Task
import com.spaceapp.data.datasource.remote.hms.entity.LoginDto
import com.spaceapp.data.datasource.remote.hms.entity.UserDto
import java.util.*
import javax.inject.Inject

class AuthDataSource @Inject constructor() {

    fun verifyUserEmail(email: String): Task<VerifyCodeResult> {
        val settings = VerifyCodeSettings.newBuilder()
            .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
            .sendInterval(30)
            .locale(Locale.ENGLISH)
            .build()

        return AGConnectAuth.getInstance().requestVerifyCode(email, settings)
    }

    fun signUp(userDto: UserDto, verifyCode: String): Task<SignInResult> {
        val emailUser = EmailUser.Builder()
            .setEmail(userDto.userEmail)
            .setVerifyCode(verifyCode)
            .setPassword(userDto.userPassword)
            .build()

        return AGConnectAuth.getInstance().createUser(emailUser)
    }

    fun login(loginDto: LoginDto): Task<SignInResult> {
        val credential = EmailAuthProvider.credentialWithPassword(loginDto.userEmail, loginDto.password)

        return AGConnectAuth.getInstance().signIn(credential)
    }
}