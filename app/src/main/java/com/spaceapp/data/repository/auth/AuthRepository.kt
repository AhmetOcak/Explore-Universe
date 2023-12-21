package com.spaceapp.data.repository.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.huawei.agconnect.auth.SignInResult
import com.huawei.agconnect.auth.VerifyCodeResult
import com.spaceapp.domain.model.auth.ForgotPassword
import com.spaceapp.domain.model.auth.Login
import com.spaceapp.domain.model.auth.SignUp
import com.spaceapp.domain.model.auth.VerifyEmail

interface AuthRepository {

    fun verifyEmailGMS(): Task<Void>?

    fun forgotPasswordGMS(forgotPassword: ForgotPassword): Task<Void>

    fun signUpGMS(signUp: SignUp): Task<AuthResult>

    fun loginGMS(login: Login): Task<AuthResult>

    fun signUpHMS(signUp: SignUp) : com.huawei.hmf.tasks.Task<SignInResult>

    fun loginHMS(login: Login): com.huawei.hmf.tasks.Task<SignInResult>

    fun verifyUserEmailHMS(verifyEmail: VerifyEmail) : com.huawei.hmf.tasks.Task<VerifyCodeResult>

    fun forgotPasswordHMS(forgotPassword: ForgotPassword): com.huawei.hmf.tasks.Task<Void>
}