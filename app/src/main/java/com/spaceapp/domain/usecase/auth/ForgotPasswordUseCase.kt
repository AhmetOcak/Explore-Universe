package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task
import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.FirebaseAuthRepositoryImpl
import com.spaceapp.data.repository.HmsAuthRepositoryImpl
import com.spaceapp.domain.model.auth.ForgotPassword
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl,
    private val hmsAuthRepositoryImpl: HmsAuthRepositoryImpl,
) {
    fun firebaseAuth(forgotPassword: ForgotPassword): Flow<Result<Task<Void>>> = flow {
        try {
            emit(Result.Success(data = firebaseAuthRepositoryImpl.forgotPassword(forgotPassword = forgotPassword)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(forgotPassword: ForgotPassword): Flow<Result<com.huawei.hmf.tasks.Task<Void>>> = flow {
        try {
            emit(Result.Success(data = hmsAuthRepositoryImpl.forgotPassword(forgotPassword = forgotPassword)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}