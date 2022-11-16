package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task
import com.huawei.agconnect.auth.VerifyCodeResult
import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.FirebaseAuthRepositoryImpl
import com.spaceapp.data.repository.HmsAuthRepositoryImpl
import com.spaceapp.domain.model.auth.VerifyEmail
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class VerifyUserEmailUseCase @Inject constructor(
    private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl,
    private val hmsAuthRepositoryImpl: HmsAuthRepositoryImpl
) {

    fun firebaseAuth(): Flow<Result<Task<Void>>> = flow {
        try {
            emit(Result.Success(data = firebaseAuthRepositoryImpl.verifyEmail()))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(verifyEmail: VerifyEmail): Flow<Result<com.huawei.hmf.tasks.Task<VerifyCodeResult>>> = flow {
        try {
            emit(Result.Success(data = hmsAuthRepositoryImpl.verifyUserEmail(verifyEmail = verifyEmail)))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}