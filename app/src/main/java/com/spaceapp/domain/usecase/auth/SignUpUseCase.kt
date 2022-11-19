package com.spaceapp.domain.usecase.auth

import com.google.android.gms.tasks.Task as gmsTask
import com.huawei.hmf.tasks.Task as hmsTask
import com.google.firebase.auth.AuthResult
import com.huawei.agconnect.auth.SignInResult
import com.spaceapp.core.common.TaskResult
import com.spaceapp.domain.model.auth.SignUp
import com.spaceapp.domain.repository.FirebaseAuthRepository
import com.spaceapp.domain.repository.HmsAuthRepository
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val firebaseAuthRepositoryImpl: FirebaseAuthRepository,
    private val hmsAuthRepositoryImpl: HmsAuthRepository,
) {
    fun firebaseAuth(signUp: SignUp) : Flow<TaskResult<gmsTask<AuthResult>>> = flow {
        try {
            emit(TaskResult.Success(data = firebaseAuthRepositoryImpl.signUp(signUp = signUp)))
        } catch (e: IOException) {
            emit(TaskResult.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(TaskResult.Error(message = e.localizedMessage))
        }
    }

    fun hmsAuth(signUp: SignUp) : Flow<TaskResult<hmsTask<SignInResult>>> = flow {
        try {
            emit(TaskResult.Success(data = hmsAuthRepositoryImpl.signUp(signUp = signUp)))
        } catch (e: IOException) {
            emit(TaskResult.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(TaskResult.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}