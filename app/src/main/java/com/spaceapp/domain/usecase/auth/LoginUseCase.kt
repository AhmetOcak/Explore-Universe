package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.huawei.agconnect.auth.SignInResult
import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.FirebaseAuthRepositoryImpl
import com.spaceapp.data.repository.HmsAuthRepositoryImpl
import com.spaceapp.domain.model.auth.Login
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl,
    private val hmsAuthRepositoryImpl: HmsAuthRepositoryImpl,
) {

    fun firebaseAuth(login: Login) : Flow<Result<Task<AuthResult>>> = flow {
        try {
            emit(Result.Success(data = firebaseAuthRepositoryImpl.login(login = login)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(login: Login) : Flow<Result<com.huawei.hmf.tasks.Task<SignInResult>>> = flow {
        try {
            emit(Result.Success(data = hmsAuthRepositoryImpl.login(login = login)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}